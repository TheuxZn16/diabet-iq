package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class VinculoMedicoPacienteJaExisteException extends BusinessException {

    public VinculoMedicoPacienteJaExisteException(UUID medicoId, UUID pacienteId) {
        super("Já existe vínculo ativo entre o médico '%s' e o paciente '%s'"
                .formatted(medicoId, pacienteId), HttpStatus.CONFLICT);
    }
}
