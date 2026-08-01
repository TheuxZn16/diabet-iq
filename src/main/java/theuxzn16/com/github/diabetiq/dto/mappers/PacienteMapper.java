package theuxzn16.com.github.diabetiq.dto.mappers;

import theuxzn16.com.github.diabetiq.dto.response.PacienteReponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioPacienteRequestDTO;
import theuxzn16.com.github.diabetiq.entity.Paciente;
import theuxzn16.com.github.diabetiq.entity.Usuario;

public class PacienteMapper {
    public static PacienteReponseDTO toDto(Paciente paciente){
        return new PacienteReponseDTO(
                paciente.getId(),
                paciente.getDataNascimento(),
                paciente.getTipoDiabetes(),
                paciente.getPesoKg(),
                paciente.getAlturaCm(),
                paciente.getGlicemiaAlvoMin(),
                paciente.getGlicemiaAlvoMax(),
                paciente.getUpdatedAt(),
                paciente.getCreatedAt(),
                UsuarioMapper.toDto(paciente.getUsuario()),
                MedicoPacienteMapper.toDtos(paciente.getVinculos())
        );
    }

    public static Paciente toEntity(UsuarioPacienteRequestDTO dto, Usuario usuario){
        return new Paciente(
                usuario,
                dto.dataNascimento(),
                dto.tipoDiabetes(),
                dto.pesoKg(),
                dto.alturaCm(),
                dto.glicemiaAlvoMin(),
                dto.glicemiaAlvoMax()
        );
    }
}
