package theuxzn16.com.github.diabetiq.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import theuxzn16.com.github.diabetiq.entity.Usuario;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {

    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final String issuer;
    private final long expirationMinutes;

    public JwtService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.issuer}") String issuer,
            @Value("${security.jwt.expiration-minutes}") long expirationMinutes
    ) {
        if (secret.length() < 32) {
            throw new IllegalStateException("security.jwt.secret deve possuir pelo menos 32 caracteres");
        }
        if (expirationMinutes <= 0) {
            throw new IllegalStateException("security.jwt.expiration-minutes deve ser maior que zero");
        }
        this.algorithm = Algorithm.HMAC256(secret);
        this.issuer = issuer;
        this.expirationMinutes = expirationMinutes;
        this.verifier = JWT.require(algorithm).withIssuer(issuer).build();
    }

    public String gerarToken(Usuario usuario) {
        Instant agora = Instant.now();
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(usuario.getEmail())
                .withClaim("usuarioId", usuario.getId().toString())
                .withClaim("tipoUsuario", usuario.getTipoUsuario().name())
                .withClaim("role", "ROLE_" + usuario.getTipoUsuario().name())
                .withClaim("tokenType", "ACCESS")
                .withIssuedAt(Date.from(agora))
                .withExpiresAt(Date.from(agora.plus(expirationMinutes, ChronoUnit.MINUTES)))
                .sign(algorithm);
    }

    public String obterEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public DecodedJWT extractClaims(String token) {
        return verifier.verify(token);
    }

    public boolean tokenValido(String token, Usuario usuario) {
        DecodedJWT jwt = extractClaims(token);
        return usuario.getEmail().equals(jwt.getSubject())
                && "ACCESS".equals(jwt.getClaim("tokenType").asString())
                && ("ROLE_" + usuario.getTipoUsuario().name()).equals(jwt.getClaim("role").asString());
    }

    public long getExpirationSeconds() {
        return expirationMinutes * 60;
    }

}
