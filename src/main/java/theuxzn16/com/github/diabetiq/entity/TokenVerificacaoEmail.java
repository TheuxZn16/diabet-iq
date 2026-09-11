package theuxzn16.com.github.diabetiq.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tokens_verificacao_email")
public class TokenVerificacaoEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "token_hash", nullable = false, unique = true, length = 64)
    private String tokenHash;

    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;

    public TokenVerificacaoEmail() {
    }

    public TokenVerificacaoEmail(Usuario usuario, String tokenHash, OffsetDateTime expiresAt) {
        this.usuario = usuario;
        this.tokenHash = tokenHash;
        this.expiresAt = expiresAt;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean expirado() {
        return !expiresAt.isAfter(OffsetDateTime.now());
    }
}
