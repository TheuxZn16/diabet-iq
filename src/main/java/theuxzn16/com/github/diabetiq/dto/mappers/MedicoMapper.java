package theuxzn16.com.github.diabetiq.dto.mappers;

import theuxzn16.com.github.diabetiq.dto.response.MedicoResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioMedicoRequestDTO;
import theuxzn16.com.github.diabetiq.entity.Medico;
import theuxzn16.com.github.diabetiq.entity.Usuario;

public class MedicoMapper {
    public static MedicoResponseDTO toDto(Medico medico){
        return new MedicoResponseDTO(
                medico.getId(),
                medico.getCrm(),
                medico.getUfCrm(),
                medico.getEspecialidade(),
                TimeMapper.toTimeZone(medico.getUpdatedAt()),
                TimeMapper.toTimeZone(medico.getCreatedAt()),
                UsuarioMapper.toDto(medico.getUsuario()),
                MedicoPacienteMapper.toDtos(medico.getVinculos())
        );
    }

    public static Medico toEntity(UsuarioMedicoRequestDTO dto, Usuario usuario){
        return new Medico(
                usuario,
                dto.crm(),
                dto.ufCrm(),
                dto.especialidade()
        );
    }
}
