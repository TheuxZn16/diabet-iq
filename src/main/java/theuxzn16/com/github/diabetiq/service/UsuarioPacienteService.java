package theuxzn16.com.github.diabetiq.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import theuxzn16.com.github.diabetiq.dto.mappers.PacienteMapper;
import theuxzn16.com.github.diabetiq.dto.mappers.UsuarioMapper;
import theuxzn16.com.github.diabetiq.dto.response.UsuarioResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioPacienteRequestDTO;
import theuxzn16.com.github.diabetiq.exception.EmailJaCadastradoException;
import theuxzn16.com.github.diabetiq.repository.PacienteRepository;
import theuxzn16.com.github.diabetiq.repository.UsuarioRepository;

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
}
