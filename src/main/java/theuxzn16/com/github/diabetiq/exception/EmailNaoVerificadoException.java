package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;

public class EmailNaoVerificadoException extends BusinessException {

    public EmailNaoVerificadoException() {
        super("O email da conta ainda não foi verificado", HttpStatus.FORBIDDEN);
    }
}
