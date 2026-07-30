package theuxzn16.com.github.diabetiq.dto.response;

import theuxzn16.com.github.diabetiq.enums.TipoUsuario;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String email,
        String nome,
        TipoUsuario tipoUsuario,
        Boolean emailVerificado,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
