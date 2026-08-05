package theuxzn16.com.github.diabetiq.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenInvalidoException extends AuthenticationException {
    public TokenInvalidoException() {
        super("Token JWT inválido ou expirado");
    }
}
