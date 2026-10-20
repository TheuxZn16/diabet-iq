package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;

public class TokenVerificacaoEmailInvalidoException extends BusinessException {

    public TokenVerificacaoEmailInvalidoException() {
        super("Link de verificação inválido ou expirado", HttpStatus.BAD_REQUEST);
    }
}
