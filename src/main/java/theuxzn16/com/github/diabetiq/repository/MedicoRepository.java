package theuxzn16.com.github.diabetiq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theuxzn16.com.github.diabetiq.entity.Medico;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {
    Boolean existsByCrmAndUfCrm(String crm, String ufCrm);
    Optional<Medico> findByUsuario_Id(UUID id);
}
