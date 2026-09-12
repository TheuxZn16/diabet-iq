package theuxzn16.com.github.diabetiq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import theuxzn16.com.github.diabetiq.entity.TokenVerificacaoEmail;

import java.util.Optional;
import java.util.UUID;

public interface TokenVerificacaoEmailRepository extends JpaRepository<TokenVerificacaoEmail, UUID> {
    Optional<TokenVerificacaoEmail> findByTokenHash(String tokenHash);

    void deleteByUsuario_Id(UUID usuarioId);
}
