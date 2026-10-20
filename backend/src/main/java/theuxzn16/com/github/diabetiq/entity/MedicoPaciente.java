package theuxzn16.com.github.diabetiq.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "medico_paciente", uniqueConstraints = {
        @UniqueConstraint(name = "uk_medico_paciente", columnNames = {"medico_id", "paciente_id"})})
public class MedicoPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "data_vinculo", nullable = false)
    private OffsetDateTime dataVinculo;

    @Column(name = "data_encerramento")
    private OffsetDateTime dataEncerramento;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    public MedicoPaciente() {
    }

    public MedicoPaciente(Medico medico, Paciente paciente) {
        this.medico = medico;
        this.paciente = paciente;
    }

    public UUID getId() {
        return id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public OffsetDateTime getDataVinculo() {
        return dataVinculo;
    }

    public void setDataVinculo(OffsetDateTime dataVinculo) {
        this.dataVinculo = dataVinculo;
    }

    public OffsetDateTime getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(OffsetDateTime dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @PrePersist
    public void prePersist() {
        this.dataVinculo = OffsetDateTime.now(ZoneOffset.of("-03:00"));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MedicoPaciente that = (MedicoPaciente) o;
        return Objects.equals(id, that.id) && Objects.equals(medico, that.medico) && Objects.equals(paciente, that.paciente) && Objects.equals(dataVinculo, that.dataVinculo) && Objects.equals(dataEncerramento, that.dataEncerramento) && Objects.equals(ativo, that.ativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medico, paciente, dataVinculo, dataEncerramento, ativo);
    }

    @Override
    public String toString() {
        return "MedicoPaciente{" +
                "id=" + id +
                ", dataVinculo=" + dataVinculo +
                ", dataEncerramento=" + dataEncerramento +
                ", ativo=" + ativo +
                '}';
    }
}
