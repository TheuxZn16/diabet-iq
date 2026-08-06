package theuxzn16.com.github.diabetiq.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import theuxzn16.com.github.diabetiq.dto.mappers.PacienteMapper;
import theuxzn16.com.github.diabetiq.dto.mappers.UsuarioMapper;
import theuxzn16.com.github.diabetiq.dto.response.PacienteReponseDTO;
import theuxzn16.com.github.diabetiq.dto.response.UsuarioResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioPacienteRequestDTO;
import theuxzn16.com.github.diabetiq.entity.Paciente;
import theuxzn16.com.github.diabetiq.entity.Usuario;
import theuxzn16.com.github.diabetiq.exception.CredenciaisInvalidasException;
import theuxzn16.com.github.diabetiq.exception.EmailJaCadastradoException;
import theuxzn16.com.github.diabetiq.exception.PacienteNaoEncontradoException;
import theuxzn16.com.github.diabetiq.exception.UsuarioNaoEncontradoException;
import theuxzn16.com.github.diabetiq.repository.PacienteRepository;
import theuxzn16.com.github.diabetiq.repository.UsuarioRepository;

import java.util.UUID;

@Service
public class UsuarioPacienteService {
    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioPacienteService(UsuarioRepository usuarioRepository, PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponseDTO create(UsuarioPacienteRequestDTO body){
        if(usuarioRepository.existsByEmail(body.email())){
            throw new EmailJaCadastradoException(body.email());
        }

        var userEntity = UsuarioMapper.toEntityPaciente(body);
        userEntity.setSenhaHash(passwordEncoder.encode(body.senha()));
        var userSalvo = usuarioRepository.save(userEntity);
        pacienteRepository.save(PacienteMapper.toEntity(body, userSalvo));
        return UsuarioMapper.toDto(userSalvo);
    }

    @Transactional
    public PacienteReponseDTO update(UsuarioPacienteRequestDTO body, UUID id) {
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        Paciente pacienteEntity = pacienteRepository.findByUsuario_Id(id).orElseThrow(() -> new PacienteNaoEncontradoException(id));

        if (!body.email().equalsIgnoreCase(usuarioEntity.getEmail())
                && usuarioRepository.existsByEmail(body.email())) {
            throw new EmailJaCadastradoException(body.email());
        }

        if (!passwordEncoder.matches(body.senha(), usuarioEntity.getSenhaHash())) {
            throw new CredenciaisInvalidasException();
        }

        usuarioEntity.setNome(body.nome());
        usuarioEntity.setEmail(body.email());

        pacienteEntity.setGlicemiaAlvoMin(body.glicemiaAlvoMin());
        pacienteEntity.setGlicemiaAlvoMax(body.glicemiaAlvoMax());
        pacienteEntity.setAlturaCm(body.alturaCm());
        pacienteEntity.setPesoKg(body.pesoKg());
        pacienteEntity.setTipoDiabetes(body.tipoDiabetes());
        pacienteEntity.setDataNascimento(body.dataNascimento());

        usuarioRepository.save(usuarioEntity);
        var paciente = pacienteRepository.save(pacienteEntity);
        return PacienteMapper.toDto(paciente);
    }
}
