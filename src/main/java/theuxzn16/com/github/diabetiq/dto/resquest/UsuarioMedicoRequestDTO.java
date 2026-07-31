package theuxzn16.com.github.diabetiq.dto.resquest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioMedicoRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres")
        String nome,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        @Size(max = 150, message = "O email deve ter no máximo 150 caracteres")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, max = 100, message = "A senha deve ter entre 8 e 100 caracteres")
        String senha,

        @NotBlank(message = "O CRM é obrigatório")
        @Pattern(regexp = "\\d{4,6}", message = "O CRM deve conter entre 4 e 6 dígitos")
        String crm,

        @NotBlank(message = "A UF do CRM é obrigatória")
        @Pattern(regexp = "[A-Z]{2}", message = "A UF deve conter 2 letras maiúsculas (ex: SP, RJ)")
        String ufCrm,

        @Size(max = 100, message = "A especialidade deve ter no máximo 100 caracteres")
        String especialidade
) {
}