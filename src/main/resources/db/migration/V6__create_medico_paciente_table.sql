CREATE TABLE medico_paciente (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    medico_id       UUID NOT NULL REFERENCES medicos(id) ON DELETE CASCADE,
    paciente_id     UUID NOT NULL REFERENCES pacientes(id) ON DELETE CASCADE,
    data_vinculo    TIMESTAMPTZ NOT NULL DEFAULT now(),
    data_encerramento TIMESTAMPTZ,
    ativo           BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT uk_medico_paciente UNIQUE (medico_id, paciente_id)
);

CREATE INDEX idx_medico_paciente_medico ON medico_paciente(medico_id);
CREATE INDEX idx_medico_paciente_paciente ON medico_paciente(paciente_id);
CREATE INDEX idx_medico_paciente_ativo ON medico_paciente(ativo);
