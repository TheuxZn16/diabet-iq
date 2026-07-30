package theuxzn16.com.github.diabetiq.dto.resquest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MedicoPacienteRequestDTO(
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        @Size(max = 150, message = "O email deve ter no máximo 150 caracteres")
        String emailPaciente
) {
}
