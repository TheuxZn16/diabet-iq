package theuxzn16.com.github.diabetiq.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import theuxzn16.com.github.diabetiq.dto.mappers.MedicoMapper;
import theuxzn16.com.github.diabetiq.dto.mappers.PacienteMapper;
import theuxzn16.com.github.diabetiq.dto.response.PerfilResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioUpdateSenhaDTO;
import theuxzn16.com.github.diabetiq.entity.Usuario;
import theuxzn16.com.github.diabetiq.exception.*;
import theuxzn16.com.github.diabetiq.repository.MedicoRepository;
import theuxzn16.com.github.diabetiq.repository.PacienteRepository;
import theuxzn16.com.github.diabetiq.repository.UsuarioRepository;

import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.passwordEncoder = passwordEncoder;
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
}
