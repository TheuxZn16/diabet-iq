package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;
import java.util.UUID;

public class PerfilNaoEncontradoException extends BusinessException {
    public PerfilNaoEncontradoException(UUID usuarioId) {
        super("Usuário '%s' não possui perfil de médico ou paciente associado".formatted(usuarioId),
                HttpStatus.NOT_FOUND);
    }
}