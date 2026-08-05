package theuxzn16.com.github.diabetiq.dto.resquest;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequestDTO(
        @NotBlank(message = "O refresh token é obrigatório")
        String refreshToken
) {
}
