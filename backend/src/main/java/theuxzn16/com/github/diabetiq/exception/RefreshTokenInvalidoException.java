package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;

public class RefreshTokenInvalidoException extends BusinessException {
    public RefreshTokenInvalidoException() {
        super("Refresh token inválido ou expirado", HttpStatus.UNAUTHORIZED);
    }
}
