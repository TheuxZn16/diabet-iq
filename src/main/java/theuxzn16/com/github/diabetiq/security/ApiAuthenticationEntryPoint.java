package theuxzn16.com.github.diabetiq.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import theuxzn16.com.github.diabetiq.exception.ErrorResponseDTO;
import theuxzn16.com.github.diabetiq.exception.TokenInvalidoException;
import theuxzn16.com.github.diabetiq.exception.TokenNaoInformadoException;

import java.io.IOException;
import java.time.OffsetDateTime;

@Component
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public ApiAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        AuthenticationException erro = exception instanceof TokenInvalidoException
                ? exception
                : new TokenNaoInformadoException();
        escreverResposta(response, HttpStatus.UNAUTHORIZED, erro.getMessage());
    }

    private void escreverResposta(HttpServletResponse response, HttpStatus status, String mensagem) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), new ErrorResponseDTO(
                OffsetDateTime.now(), status.value(), status.getReasonPhrase(), mensagem, null));
    }
}
