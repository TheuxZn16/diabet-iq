CREATE TABLE medicos (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id      UUID NOT NULL UNIQUE REFERENCES usuarios(id) ON DELETE CASCADE,
    crm             VARCHAR(20) NOT NULL,
    uf_crm          CHAR(2) NOT NULL,
    especialidade   VARCHAR(100),
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT uk_medicos_crm_uf UNIQUE (crm, uf_crm)
);

CREATE INDEX idx_medicos_crm ON medicos(crm);

CREATE TRIGGER trg_medicos_updated_at
BEFORE UPDATE ON medicos
FOR EACH ROW EXECUTE FUNCTION fn_atualizar_updated_at();
