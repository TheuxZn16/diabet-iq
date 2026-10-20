package theuxzn16.com.github.diabetiq.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import theuxzn16.com.github.diabetiq.dto.mappers.MedicoPacienteMapper;
import theuxzn16.com.github.diabetiq.dto.response.MedicoPacienteResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.MedicoPacienteRequestDTO;
import theuxzn16.com.github.diabetiq.entity.Medico;
import theuxzn16.com.github.diabetiq.entity.MedicoPaciente;
import theuxzn16.com.github.diabetiq.entity.Paciente;
import theuxzn16.com.github.diabetiq.entity.Usuario;
import theuxzn16.com.github.diabetiq.exception.AcessoNegadoException;
import theuxzn16.com.github.diabetiq.exception.MedicoNaoEncontradoException;
import theuxzn16.com.github.diabetiq.exception.PacienteNaoEncontradoException;
import theuxzn16.com.github.diabetiq.exception.VinculoMedicoPacienteJaExisteException;
import theuxzn16.com.github.diabetiq.repository.MedicoPacienteRepository;
import theuxzn16.com.github.diabetiq.repository.MedicoRepository;
import theuxzn16.com.github.diabetiq.repository.PacienteRepository;

@Service
public class MedicoPacienteService {
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoPacienteRepository medicoPacienteRepository;

    public MedicoPacienteService(MedicoRepository medicoRepository, PacienteRepository pacienteRepository, MedicoPacienteRepository medicoPacienteRepository) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoPacienteRepository = medicoPacienteRepository;
    }

    @Transactional
    public MedicoPacienteResponseDTO create(MedicoPacienteRequestDTO body) {
        Usuario usuarioAutenticado = getUsuarioAutenticado();

        Medico medico = medicoRepository.findByUsuario_Id(usuarioAutenticado.getId())
                .orElseThrow(() -> new MedicoNaoEncontradoException(usuarioAutenticado.getId()));

        Paciente paciente = pacienteRepository.findByUsuario_Email(body.emailPaciente())
                .orElseThrow(() -> new PacienteNaoEncontradoException(body.emailPaciente()));

        if (medicoPacienteRepository.existsByMedicoAndPaciente(medico, paciente)) {
            throw new VinculoMedicoPacienteJaExisteException(medico.getId(), paciente.getId());
        }

        MedicoPaciente medicoPaciente = medicoPacienteRepository.save(new MedicoPaciente(medico, paciente));
        return MedicoPacienteMapper.toDto(medicoPaciente);
    }

    private Usuario getUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Usuario usuario)) {
            throw new AcessoNegadoException();
        }

        return usuario;
    }
}
