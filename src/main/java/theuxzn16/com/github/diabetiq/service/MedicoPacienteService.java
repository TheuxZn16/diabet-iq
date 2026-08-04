package theuxzn16.com.github.diabetiq.service;

import org.springframework.stereotype.Service;
import theuxzn16.com.github.diabetiq.dto.mappers.MedicoPacienteMapper;
import theuxzn16.com.github.diabetiq.dto.response.MedicoPacienteResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.MedicoPacienteRequestDTO;
import theuxzn16.com.github.diabetiq.entity.Medico;
import theuxzn16.com.github.diabetiq.entity.MedicoPaciente;
import theuxzn16.com.github.diabetiq.entity.Paciente;
import theuxzn16.com.github.diabetiq.exception.MedicoNaoEncontradoException;
import theuxzn16.com.github.diabetiq.exception.PacienteNaoEncontradoException;
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

    public MedicoPacienteResponseDTO create(MedicoPacienteRequestDTO body){
        Medico medico = medicoRepository.findByUsuario_Id(body.usuarioMedicoId()).orElseThrow(() -> new MedicoNaoEncontradoException(body.usuarioMedicoId()));
        Paciente paciente = pacienteRepository.findByUsuario_Email(body.emailPaciente()).orElseThrow(() -> new PacienteNaoEncontradoException(body.emailPaciente()));

        MedicoPaciente medicoPaciente = medicoPacienteRepository.save(new MedicoPaciente(medico, paciente));
        return MedicoPacienteMapper.toDto(medicoPaciente);
    }
}
