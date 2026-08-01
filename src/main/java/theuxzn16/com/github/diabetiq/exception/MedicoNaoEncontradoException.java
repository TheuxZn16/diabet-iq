package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class MedicoNaoEncontradoException extends BusinessException {

    public MedicoNaoEncontradoException(UUID id) {
        super("Médico com id '%s' não encontrado".formatted(id), HttpStatus.NOT_FOUND);
    }
}
