package theuxzn16.com.github.diabetiq.dto.mappers;

import theuxzn16.com.github.diabetiq.dto.response.MedicoPacienteResponseDTO;
import theuxzn16.com.github.diabetiq.entity.Medico;
import theuxzn16.com.github.diabetiq.entity.MedicoPaciente;

import java.util.ArrayList;
import java.util.List;

public class MedicoPacienteMapper {
    public static List<MedicoPacienteResponseDTO> toDtos(List<MedicoPaciente> medicosPacientes){
        List<MedicoPacienteResponseDTO> medicosPacientesDto = new ArrayList<>();
        for(MedicoPaciente medicoPaciente : medicosPacientes ){
            medicosPacientesDto.add(toDto(medicoPaciente));
        }
        return medicosPacientesDto;
    }

    public static MedicoPacienteResponseDTO toDto(MedicoPaciente medicoPaciente){
        return new MedicoPacienteResponseDTO(
                medicoPaciente.getId(),
                medicoPaciente.getAtivo()
        );
    }

}
