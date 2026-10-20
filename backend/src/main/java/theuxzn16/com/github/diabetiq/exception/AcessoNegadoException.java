package theuxzn16.com.github.diabetiq.exception;

import org.springframework.security.access.AccessDeniedException;

public class AcessoNegadoException extends AccessDeniedException {
    public AcessoNegadoException() {
        super("Você não possui permissão para acessar este recurso");
    }
}
