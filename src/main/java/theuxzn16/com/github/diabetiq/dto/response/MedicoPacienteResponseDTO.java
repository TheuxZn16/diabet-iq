package theuxzn16.com.github.diabetiq.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

public record MedicoPacienteResponseDTO(
        UUID id,
        String nomeMedico,
        String nomePaciente,
        OffsetDateTime dataVinculo,
        OffsetDateTime dataEncerramento,
        Boolean ativo
) {
}
