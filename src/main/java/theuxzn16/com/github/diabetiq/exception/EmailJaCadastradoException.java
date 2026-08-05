package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;

public class EmailJaCadastradoException extends BusinessException {

    public EmailJaCadastradoException(String email) {
        super("O email '%s' já está cadastrado".formatted(email), HttpStatus.CONFLICT);
    }
}
