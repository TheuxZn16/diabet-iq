package theuxzn16.com.github.diabetiq.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import theuxzn16.com.github.diabetiq.enums.TipoDiabetes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "pacientes")
public class Paciente extends AuditoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_diabetes", nullable = false, length = 20)
    private TipoDiabetes tipoDiabetes;

    @Column(name = "peso_kg", precision = 5, scale = 2)
    private BigDecimal pesoKg;

    @Column(name = "altura_cm", precision = 5, scale = 2)
    private BigDecimal alturaCm;

    @Column(name = "glicemia_alvo_min", nullable = false, precision = 5, scale = 2)
    private BigDecimal glicemiaAlvoMin = BigDecimal.valueOf(70);

    @Column(name = "glicemia_alvo_max", nullable = false, precision = 5, scale = 2)
    private BigDecimal glicemiaAlvoMax = BigDecimal.valueOf(180);

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicoPaciente> vinculos = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(Usuario usuario, LocalDate dataNascimento, TipoDiabetes tipoDiabetes, BigDecimal pesoKg, BigDecimal alturaCm, BigDecimal glicemiaAlvoMin, BigDecimal glicemiaAlvoMax) {
        this.usuario = usuario;
        this.dataNascimento = dataNascimento;
        this.tipoDiabetes = tipoDiabetes;
        this.pesoKg = pesoKg;
        this.alturaCm = alturaCm;
        this.glicemiaAlvoMin = glicemiaAlvoMin;
        this.glicemiaAlvoMax = glicemiaAlvoMax;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public TipoDiabetes getTipoDiabetes() {
        return tipoDiabetes;
    }

    public void setTipoDiabetes(TipoDiabetes tipoDiabetes) {
        this.tipoDiabetes = tipoDiabetes;
    }

    public BigDecimal getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }

    public BigDecimal getAlturaCm() {
        return alturaCm;
    }

    public void setAlturaCm(BigDecimal alturaCm) {
        this.alturaCm = alturaCm;
    }

    public BigDecimal getGlicemiaAlvoMin() {
        return glicemiaAlvoMin;
    }

    public void setGlicemiaAlvoMin(BigDecimal glicemiaAlvoMin) {
        this.glicemiaAlvoMin = glicemiaAlvoMin;
    }

    public BigDecimal getGlicemiaAlvoMax() {
        return glicemiaAlvoMax;
    }

    public void setGlicemiaAlvoMax(BigDecimal glicemiaAlvoMax) {
        this.glicemiaAlvoMax = glicemiaAlvoMax;
    }

    public List<MedicoPaciente> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<MedicoPaciente> vinculos) {
        this.vinculos = vinculos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id) && Objects.equals(usuario, paciente.usuario) && Objects.equals(dataNascimento, paciente.dataNascimento) && tipoDiabetes == paciente.tipoDiabetes && Objects.equals(pesoKg, paciente.pesoKg) && Objects.equals(alturaCm, paciente.alturaCm) && Objects.equals(glicemiaAlvoMin, paciente.glicemiaAlvoMin) && Objects.equals(glicemiaAlvoMax, paciente.glicemiaAlvoMax) && Objects.equals(createdAt, paciente.createdAt) && Objects.equals(updatedAt, paciente.updatedAt) && Objects.equals(vinculos, paciente.vinculos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, dataNascimento, tipoDiabetes, pesoKg, alturaCm, glicemiaAlvoMin, glicemiaAlvoMax, createdAt, updatedAt, vinculos);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", dataNascimento=" + dataNascimento +
                ", tipoDiabetes=" + tipoDiabetes +
                ", pesoKg=" + pesoKg +
                ", alturaCm=" + alturaCm +
                ", glicemiaAlvoMin=" + glicemiaAlvoMin +
                ", glicemiaAlvoMax=" + glicemiaAlvoMax +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
