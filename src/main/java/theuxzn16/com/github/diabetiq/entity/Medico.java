package theuxzn16.com.github.diabetiq.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "medicos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_medicos_crm_uf", columnNames = {"crm", "uf_crm"})})
public class Medico extends AuditoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "crm", nullable = false, length = 20)
    private String crm;

    @Column(name = "uf_crm", nullable = false, length = 2)
    private String ufCrm;

    @Column(name = "especialidade", length = 100)
    private String especialidade;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicoPaciente> vinculos = new ArrayList<>();

    public Medico() {
    }

    public Medico(Usuario usuario, String crm, String ufCrm, String especialidade) {
        this.usuario = usuario;
        this.crm = crm;
        this.ufCrm = ufCrm;
        this.especialidade = especialidade;
    }

    public UUID getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getUfCrm() {
        return ufCrm;
    }

    public void setUfCrm(String ufCrm) {
        this.ufCrm = ufCrm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<MedicoPaciente> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<MedicoPaciente> vinculos) {
        this.vinculos = vinculos;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", crm='" + crm + '\'' +
                ", ufCrm='" + ufCrm + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medico medico)) return false;
        return id != null && id.equals(medico.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
