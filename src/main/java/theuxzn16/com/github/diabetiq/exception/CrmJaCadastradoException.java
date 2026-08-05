package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;

public class CrmJaCadastradoException extends BusinessException {

    public CrmJaCadastradoException(String crm, String ufCrm) {
        super("O CRM '%s/%s' já está cadastrado".formatted(crm, ufCrm), HttpStatus.CONFLICT);
    }
}
