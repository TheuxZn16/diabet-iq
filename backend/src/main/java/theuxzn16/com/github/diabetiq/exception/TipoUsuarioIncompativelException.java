package theuxzn16.com.github.diabetiq.exception;

import org.springframework.http.HttpStatus;
import theuxzn16.com.github.diabetiq.enums.TipoUsuario;

public class TipoUsuarioIncompativelException extends BusinessException {

    public TipoUsuarioIncompativelException(TipoUsuario esperado, TipoUsuario recebido) {
        super("Operação requer usuário do tipo '%s', mas o usuário é do tipo '%s'"
                .formatted(esperado, recebido), HttpStatus.BAD_REQUEST);
    }
}
