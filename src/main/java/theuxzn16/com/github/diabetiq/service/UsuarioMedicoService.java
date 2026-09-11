package theuxzn16.com.github.diabetiq.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import theuxzn16.com.github.diabetiq.dto.mappers.MedicoMapper;
import theuxzn16.com.github.diabetiq.dto.mappers.UsuarioMapper;
import theuxzn16.com.github.diabetiq.dto.response.MedicoResponseDTO;
import theuxzn16.com.github.diabetiq.dto.response.UsuarioResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioMedicoRequestDTO;
import theuxzn16.com.github.diabetiq.entity.Medico;
import theuxzn16.com.github.diabetiq.entity.Usuario;
import theuxzn16.com.github.diabetiq.exception.*;
import theuxzn16.com.github.diabetiq.repository.MedicoRepository;
import theuxzn16.com.github.diabetiq.repository.UsuarioRepository;

import java.util.UUID;

@Service
public class UsuarioMedicoService {
    private final UsuarioRepository usuarioRepository;
    private final MedicoRepository medicoRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioService usuarioService;

    public UsuarioMedicoService(UsuarioRepository usuarioRepository, MedicoRepository medicoRepository, PasswordEncoder passwordEncoder,
                               UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.medicoRepository = medicoRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioService = usuarioService;
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
        usuarioService.enviarVerificacaoEmail(userSalvo);
        return UsuarioMapper.toDto(userSalvo);
    }

    @Transactional
    public MedicoResponseDTO update(UsuarioMedicoRequestDTO body, UUID id) {
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        Medico medicoEntity = medicoRepository.findByUsuario_Id(id).orElseThrow(() -> new MedicoNaoEncontradoException(id));

        if (!body.email().equalsIgnoreCase(usuarioEntity.getEmail())
                && usuarioRepository.existsByEmail(body.email())) {
            throw new EmailJaCadastradoException(body.email());
        }

        if (!passwordEncoder.matches(body.senha(), usuarioEntity.getSenhaHash())) {
            throw new CredenciaisInvalidasException();
        }

        boolean emailAlterado = !body.email().equalsIgnoreCase(usuarioEntity.getEmail());
        usuarioEntity.setNome(body.nome());
        usuarioEntity.setEmail(body.email());
        if (emailAlterado) {
            usuarioEntity.setEmailVerificado(false);
            usuarioService.enviarVerificacaoEmail(usuarioEntity);
        }

        medicoEntity.setCrm(body.crm());
        medicoEntity.setUfCrm(body.ufCrm());
        medicoEntity.setEspecialidade(body.especialidade());

        usuarioRepository.save(usuarioEntity);
        var medico = medicoRepository.save(medicoEntity);
        return MedicoMapper.toDto(medico);
    }
}
