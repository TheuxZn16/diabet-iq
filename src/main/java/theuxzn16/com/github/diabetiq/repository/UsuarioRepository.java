package theuxzn16.com.github.diabetiq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theuxzn16.com.github.diabetiq.entity.Usuario;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Boolean existsByEmail(String email);
}
