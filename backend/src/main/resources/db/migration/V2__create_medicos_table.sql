CREATE TABLE medicos (
    id              UUID PRIMARY KEY,
    usuario_id      UUID NOT NULL UNIQUE REFERENCES usuarios(id) ON DELETE CASCADE,
    crm             VARCHAR(20) NOT NULL,
    uf_crm          CHAR(2) NOT NULL,
    especialidade   VARCHAR(100),
    created_at      TIMESTAMPTZ NOT NULL default now(),
    updated_at      TIMESTAMPTZ NOT NULL default now(),
    CONSTRAINT uk_medicos_crm_uf UNIQUE (crm, uf_crm)
);

CREATE INDEX idx_medicos_crm ON medicos(crm);

