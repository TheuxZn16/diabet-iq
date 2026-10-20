package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class UsuarioNaoEncontradoException extends BusinessException {

    public UsuarioNaoEncontradoException(UUID id) {
        super("Usuário com id '%s' não encontrado".formatted(id), HttpStatus.NOT_FOUND);
    }

    public UsuarioNaoEncontradoException(String email) {
        super("Usuário com email '%s' não encontrado".formatted(email), HttpStatus.NOT_FOUND);
    }
}
