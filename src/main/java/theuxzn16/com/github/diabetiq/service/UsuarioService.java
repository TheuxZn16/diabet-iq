package theuxzn16.com.github.diabetiq.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import theuxzn16.com.github.diabetiq.dto.mappers.MedicoMapper;
import theuxzn16.com.github.diabetiq.dto.mappers.PacienteMapper;
import theuxzn16.com.github.diabetiq.dto.response.PerfilResponseDTO;
import theuxzn16.com.github.diabetiq.entity.Usuario;
import theuxzn16.com.github.diabetiq.exception.AcessoNegadoException;
import theuxzn16.com.github.diabetiq.exception.MedicoNaoEncontradoException;
import theuxzn16.com.github.diabetiq.exception.PacienteNaoEncontradoException;
import theuxzn16.com.github.diabetiq.exception.PerfilNaoEncontradoException;
import theuxzn16.com.github.diabetiq.exception.UsuarioNaoEncontradoException;
import theuxzn16.com.github.diabetiq.repository.MedicoRepository;
import theuxzn16.com.github.diabetiq.repository.PacienteRepository;
import theuxzn16.com.github.diabetiq.repository.UsuarioRepository;

import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }


    @Transactional(readOnly = true)
    public PerfilResponseDTO findById(UUID id){
        Usuario usuarioAutenticado = (Usuario) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (!usuarioAutenticado.getId().equals(id)) {
            throw new AcessoNegadoException();
        }

        Usuario user = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        return switch (user.getTipoUsuario()){
            case MEDICO -> MedicoMapper.toDto(medicoRepository.findByUsuario_Id(id).orElseThrow(() -> new MedicoNaoEncontradoException(id)));
            case PACIENTE -> PacienteMapper.toDto(pacienteRepository.findByUsuario_Id(id).orElseThrow(() -> new PacienteNaoEncontradoException(id)));
            case ADMIN -> throw new PerfilNaoEncontradoException(id);
        };
    }
}
