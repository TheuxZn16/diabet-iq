package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class VinculoMedicoPacienteNaoEncontradoException extends BusinessException {

    public VinculoMedicoPacienteNaoEncontradoException(UUID id) {
        super("Vínculo médico-paciente com id '%s' não encontrado".formatted(id), HttpStatus.NOT_FOUND);
    }
}
