package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class PerfilJaCadastradoException extends BusinessException {

    public PerfilJaCadastradoException(UUID usuarioId) {
        super("O usuário '%s' já possui um perfil associado".formatted(usuarioId), HttpStatus.CONFLICT);
    }
}
