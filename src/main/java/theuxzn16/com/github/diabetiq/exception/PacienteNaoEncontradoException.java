package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class PacienteNaoEncontradoException extends BusinessException {

    public PacienteNaoEncontradoException(UUID id) {
        super("Paciente com id '%s' não encontrado".formatted(id), HttpStatus.NOT_FOUND);
    }
}
