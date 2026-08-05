package theuxzn16.com.github.diabetiq.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import theuxzn16.com.github.diabetiq.dto.resquest.LoginRequestDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.RefreshTokenRequestDTO;
import theuxzn16.com.github.diabetiq.dto.response.TokenResponseDTO;
import theuxzn16.com.github.diabetiq.entity.Usuario;
import theuxzn16.com.github.diabetiq.entity.RefreshToken;
import theuxzn16.com.github.diabetiq.exception.CredenciaisInvalidasException;
import theuxzn16.com.github.diabetiq.exception.RefreshTokenInvalidoException;
import theuxzn16.com.github.diabetiq.repository.RefreshTokenRepository;
import theuxzn16.com.github.diabetiq.security.JwtService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

@Service
public class AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final long refreshExpirationDays;
    private final SecureRandom secureRandom = new SecureRandom();

    public AutenticacaoService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            RefreshTokenRepository refreshTokenRepository,
            @Value("${security.jwt.refresh-expiration-days}") long refreshExpirationDays
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenRepository = refreshTokenRepository;
        if (refreshExpirationDays <= 0) {
            throw new IllegalStateException("security.jwt.refresh-expiration-days deve ser maior que zero");
        }
        this.refreshExpirationDays = refreshExpirationDays;
    }

    @Transactional
    public TokenResponseDTO login(LoginRequestDTO request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(request.email(), request.senha()));
            Usuario usuario = (Usuario) authentication.getPrincipal();
            return criarRespostaComTokens(usuario);
        } catch (BadCredentialsException ex) {
            throw new CredenciaisInvalidasException();
        }
    }

    @Transactional
    public TokenResponseDTO refresh(RefreshTokenRequestDTO request) {
        String tokenHash = calcularHash(request.refreshToken());
        RefreshToken refreshToken = refreshTokenRepository.findByTokenHashAndRevokedAtIsNull(tokenHash)
                .orElseThrow(RefreshTokenInvalidoException::new);
        if (refreshToken.expirado()) {
            refreshToken.revogar();
            throw new RefreshTokenInvalidoException();
        }

        refreshToken.revogar();
        return criarRespostaComTokens(refreshToken.getUsuario());
    }

    private TokenResponseDTO criarRespostaComTokens(Usuario usuario) {
        String refreshToken = gerarRefreshToken();
        refreshTokenRepository.save(new RefreshToken(
                usuario,
                calcularHash(refreshToken),
                OffsetDateTime.now(ZoneOffset.UTC).plusDays(refreshExpirationDays)
        ));
        return new TokenResponseDTO(
                jwtService.gerarToken(usuario),
                "Bearer",
                jwtService.getExpirationSeconds(),
                refreshToken,
                refreshExpirationDays * 24 * 60 * 60
        );
    }

    private String gerarRefreshToken() {
        byte[] bytes = new byte[64];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private String calcularHash(String token) {
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(token.getBytes(StandardCharsets.UTF_8));
            return java.util.HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("SHA-256 não está disponível", ex);
        }
    }
}
