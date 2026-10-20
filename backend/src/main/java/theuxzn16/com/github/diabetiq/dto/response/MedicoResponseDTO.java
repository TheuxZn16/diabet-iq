package theuxzn16.com.github.diabetiq.dto.response;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record MedicoResponseDTO(
        UUID id,
        String crm,
        String ufCrm,
        String especialidade,
        OffsetDateTime updatedAt,
        OffsetDateTime createdAt,
        UsuarioResponseDTO usuario,
        List<MedicoPacienteResponseDTO> vinculos
) implements PerfilResponseDTO {
}
