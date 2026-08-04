CREATE TABLE usuarios (
    id              UUID PRIMARY KEY,
    nome            VARCHAR(150) NOT NULL,
    email           VARCHAR(150) NOT NULL UNIQUE,
    senha_hash      VARCHAR(255) NOT NULL,
    tipo_usuario    VARCHAR(20) NOT NULL CHECK (tipo_usuario IN ('MEDICO', 'PACIENTE', 'ADMIN')),
    email_verificado BOOLEAN NOT NULL DEFAULT FALSE,
    created_at      TIMESTAMPTZ NOT NULL default now(),
    updated_at      TIMESTAMPTZ NOT NULL default now()
);

CREATE INDEX idx_usuarios_email ON usuarios(email);
CREATE INDEX idx_usuarios_tipo_usuario ON usuarios(tipo_usuario);

