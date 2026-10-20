package theuxzn16.com.github.diabetiq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theuxzn16.com.github.diabetiq.entity.Medico;
import theuxzn16.com.github.diabetiq.entity.MedicoPaciente;
import theuxzn16.com.github.diabetiq.entity.Paciente;

import java.util.UUID;

@Repository
public interface MedicoPacienteRepository extends JpaRepository<MedicoPaciente, UUID> {
    boolean existsByMedicoAndPaciente(Medico medico, Paciente paciente);
}
