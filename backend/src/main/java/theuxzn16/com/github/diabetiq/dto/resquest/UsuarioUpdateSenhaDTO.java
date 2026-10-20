package theuxzn16.com.github.diabetiq.dto.resquest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateSenhaDTO(
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, max = 100, message = "A senha deve ter entre 8 e 100 caracteres")
        String senhaAntiga,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, max = 100, message = "A senha deve ter entre 8 e 100 caracteres")
        String senhaNova
) {
}
