package theuxzn16.com.github.diabetiq.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import theuxzn16.com.github.diabetiq.dto.mappers.MedicoMapper;
import theuxzn16.com.github.diabetiq.dto.mappers.PacienteMapper;
import theuxzn16.com.github.diabetiq.dto.response.PerfilResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioUpdateSenhaDTO;
import theuxzn16.com.github.diabetiq.entity.Usuario;
import theuxzn16.com.github.diabetiq.entity.TokenVerificacaoEmail;
import theuxzn16.com.github.diabetiq.exception.*;
import theuxzn16.com.github.diabetiq.repository.MedicoRepository;
import theuxzn16.com.github.diabetiq.repository.PacienteRepository;
import theuxzn16.com.github.diabetiq.repository.UsuarioRepository;
import theuxzn16.com.github.diabetiq.repository.TokenVerificacaoEmailRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenVerificacaoEmailRepository tokenVerificacaoEmailRepository;
    private final JavaMailSender mailSender;
    private final String urlVerificacaoEmail;
    private final String emailRemetente;
    private final long expiracaoEmailHoras;
    private final SecureRandom secureRandom = new SecureRandom();

    public UsuarioService(UsuarioRepository usuarioRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository,
                          PasswordEncoder passwordEncoder, TokenVerificacaoEmailRepository tokenVerificacaoEmailRepository,
                          JavaMailSender mailSender, @Value("${security.email-verificacao.url}") String urlVerificacaoEmail,
                          @Value("${app.mail.from}") String emailRemetente,
                          @Value("${security.email-verificacao.expiracao-horas}") long expiracaoEmailHoras) {
        this.usuarioRepository = usuarioRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenVerificacaoEmailRepository = tokenVerificacaoEmailRepository;
        this.mailSender = mailSender;
        this.urlVerificacaoEmail = urlVerificacaoEmail;
        this.emailRemetente = emailRemetente;
        this.expiracaoEmailHoras = expiracaoEmailHoras;
    }


    @Transactional(readOnly = true)
    public PerfilResponseDTO findById(UUID id){
        Usuario user = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        return switch (user.getTipoUsuario()){
            case MEDICO -> MedicoMapper.toDto(medicoRepository.findByUsuario_Id(id).orElseThrow(() -> new MedicoNaoEncontradoException(id)));
            case PACIENTE -> PacienteMapper.toDto(pacienteRepository.findByUsuario_Id(id).orElseThrow(() -> new PacienteNaoEncontradoException(id)));
            case ADMIN -> throw new PerfilNaoEncontradoException(id);
        };
    }

    @Transactional
    public void delete(UUID id){
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public void updatePassword(UUID id, UsuarioUpdateSenhaDTO body){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        if(!passwordEncoder.matches(body.senhaAntiga(), usuario.getSenhaHash())){
            throw new CredenciaisInvalidasException();
        }

        usuario.setSenhaHash(passwordEncoder.encode(body.senhaNova()));

        usuarioRepository.save(usuario);
    }

    @Transactional
    public void enviarVerificacaoEmail(Usuario usuario) {
        String token = gerarToken();
        tokenVerificacaoEmailRepository.deleteByUsuario_Id(usuario.getId());
        tokenVerificacaoEmailRepository.save(new TokenVerificacaoEmail(
                usuario, calcularHash(token), OffsetDateTime.now().plusHours(expiracaoEmailHoras)
        ));

        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom(emailRemetente);
        mensagem.setTo(usuario.getEmail());
        mensagem.setSubject("Confirme seu email - DiabetIQ");
        mensagem.setText("Olá, " + usuario.getNome() + ",\n\nConfirme seu email acessando o link abaixo:\n"
                + urlVerificacaoEmail + "?token=" + token
                + "\n\nEste link expira em " + expiracaoEmailHoras + " horas.");
        mailSender.send(mensagem);
    }

    @Transactional
    public void verificarEmail(String token) {
        TokenVerificacaoEmail tokenVerificacao = tokenVerificacaoEmailRepository.findByTokenHash(calcularHash(token))
                .orElseThrow(TokenVerificacaoEmailInvalidoException::new);
        if (tokenVerificacao.expirado()) {
            tokenVerificacaoEmailRepository.delete(tokenVerificacao);
            throw new TokenVerificacaoEmailInvalidoException();
        }

        Usuario usuario = tokenVerificacao.getUsuario();
        usuario.setEmailVerificado(true);
        usuarioRepository.save(usuario);
        tokenVerificacaoEmailRepository.delete(tokenVerificacao);
    }

    private String gerarToken() {
        byte[] bytes = new byte[32];
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
