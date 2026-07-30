package theuxzn16.com.github.diabetiq.dto.resquest;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import theuxzn16.com.github.diabetiq.enums.TipoDiabetes;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UsuarioPacienteRequestDTO(

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

        @NotNull(message = "A data de nascimento é obrigatória")
        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dataNascimento,

        @NotNull(message = "O tipo de diabetes é obrigatório")
        TipoDiabetes tipoDiabetes,

        @DecimalMin(value = "1.0", message = "O peso deve ser maior que 0")
        @DecimalMax(value = "500.0", message = "O peso informado é inválido")
        BigDecimal pesoKg,

        @DecimalMin(value = "30.0", message = "A altura deve ser maior que 30 cm")
        @DecimalMax(value = "272.0", message = "A altura informada é inválida")
        BigDecimal alturaCm,

        @NotNull(message = "A glicemia alvo mínima é obrigatória")
        @DecimalMin(value = "40.0", message = "A glicemia alvo mínima deve ser maior ou igual a 40")
        BigDecimal glicemiaAlvoMin,

        @NotNull(message = "A glicemia alvo máxima é obrigatória")
        @DecimalMax(value = "400.0", message = "A glicemia alvo máxima deve ser menor ou igual a 400")
        BigDecimal glicemiaAlvoMax
) {

        @AssertTrue(message = "A glicemia alvo máxima deve ser maior que a glicemia alvo mínima")
        public boolean isIntervaloGlicemiaValido() {
                if (glicemiaAlvoMin == null || glicemiaAlvoMax == null) {
                        return true;                 }
                return glicemiaAlvoMax.compareTo(glicemiaAlvoMin) > 0;
        }
}