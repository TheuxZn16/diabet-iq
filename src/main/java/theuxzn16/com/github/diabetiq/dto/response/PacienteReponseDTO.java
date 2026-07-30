package theuxzn16.com.github.diabetiq.dto.response;

import theuxzn16.com.github.diabetiq.entity.MedicoPaciente;
import theuxzn16.com.github.diabetiq.enums.TipoDiabetes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record PacienteReponseDTO(
    UUID id,
    LocalDate dataNascimento,
    TipoDiabetes tipoDiabetes,
    BigDecimal pesoKg,
    BigDecimal alturaCm,
    BigDecimal glicemiaAlvoMin,
    BigDecimal glicemiaAlvoMax,
    OffsetDateTime updatedAt,
    OffsetDateTime createdAt,
    List<MedicoPaciente> vinculos
) {
}
