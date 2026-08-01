package theuxzn16.com.github.diabetiq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theuxzn16.com.github.diabetiq.entity.Medico;
import theuxzn16.com.github.diabetiq.entity.Paciente;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    Optional<Paciente> findByUsuario_Id(UUID id);
}
