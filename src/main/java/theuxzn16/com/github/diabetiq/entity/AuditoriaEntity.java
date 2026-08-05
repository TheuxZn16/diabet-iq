package theuxzn16.com.github.diabetiq.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@MappedSuperclass
public abstract class AuditoriaEntity {

    private static final ZoneId ZONA_BRASIL = ZoneId.of("America/Sao_Paulo");

    @Column(name = "created_at", nullable = false, updatable = false)
    protected OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    protected OffsetDateTime updatedAt;

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void aoPersistir() {
        OffsetDateTime agora = OffsetDateTime.now(ZONA_BRASIL);
        this.createdAt = agora;
        this.updatedAt = agora;
    }

    @PreUpdate
    protected void aoAtualizar() {
        this.updatedAt = OffsetDateTime.now(ZONA_BRASIL);
    }
}