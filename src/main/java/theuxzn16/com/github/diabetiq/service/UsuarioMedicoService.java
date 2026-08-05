package theuxzn16.com.github.diabetiq.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import theuxzn16.com.github.diabetiq.dto.mappers.MedicoMapper;
import theuxzn16.com.github.diabetiq.dto.mappers.UsuarioMapper;
import theuxzn16.com.github.diabetiq.dto.response.UsuarioResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioMedicoRequestDTO;
import theuxzn16.com.github.diabetiq.exception.CrmJaCadastradoException;
import theuxzn16.com.github.diabetiq.exception.EmailJaCadastradoException;
import theuxzn16.com.github.diabetiq.repository.MedicoRepository;
import theuxzn16.com.github.diabetiq.repository.UsuarioRepository;

@Service
public class UsuarioMedicoService {
    private final UsuarioRepository usuarioRepository;
    private final MedicoRepository medicoRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioMedicoService(UsuarioRepository usuarioRepository, MedicoRepository medicoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.medicoRepository = medicoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponseDTO create(UsuarioMedicoRequestDTO body){
        if(usuarioRepository.existsByEmail(body.email())){
            throw new EmailJaCadastradoException(body.email());
        }
        if(medicoRepository.existsByCrmAndUfCrm(body.crm(), body.ufCrm())){
            throw new CrmJaCadastradoException(body.crm(), body.ufCrm());
        }

        var userEntity = UsuarioMapper.toEntityMedico(body);
        userEntity.setSenhaHash(passwordEncoder.encode(body.senha()));
        var userSalvo = usuarioRepository.save(userEntity);
        medicoRepository.save(MedicoMapper.toEntity(body, userSalvo));
        return UsuarioMapper.toDto(userSalvo);
    }
}
