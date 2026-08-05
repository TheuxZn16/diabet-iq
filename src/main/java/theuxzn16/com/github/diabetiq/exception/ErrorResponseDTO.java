package theuxzn16.com.github.diabetiq.exception;

import java.time.OffsetDateTime;
import java.util.List;

public record ErrorResponseDTO(
        OffsetDateTime timestamp,
        int status,
        String erro,
        String mensagem,
        List<CampoErro> campos
) {
    public record CampoErro(String campo, String mensagem) {
    }
}
