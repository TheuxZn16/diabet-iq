package theuxzn16.com.github.diabetiq.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenNaoInformadoException extends AuthenticationException {
    public TokenNaoInformadoException() {
        super("Token de autenticação não informado");
    }
}
