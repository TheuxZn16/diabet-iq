package theuxzn16.com.github.diabetiq.dto.response;

import theuxzn16.com.github.diabetiq.entity.MedicoPaciente;

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
        List<MedicoPaciente> vinculos
) {
}
