CREATE TABLE pacientes (
    id                              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id                      UUID NOT NULL UNIQUE REFERENCES usuarios(id) ON DELETE CASCADE,
    data_nascimento                 DATE NOT NULL,
    tipo_diabetes                   VARCHAR(20) NOT NULL CHECK (
        tipo_diabetes IN ('TIPO_1', 'TIPO_2', 'GESTACIONAL', 'LADA', 'MODY', 'OUTRO')
    ),
    peso_kg                         NUMERIC(5,2),
    altura_cm                       NUMERIC(5,2),
    glicemia_alvo_min               NUMERIC(5,2) NOT NULL DEFAULT 70,
    glicemia_alvo_max               NUMERIC(5,2) NOT NULL DEFAULT 180,
    created_at                      TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at                      TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT ck_pacientes_alvo CHECK (glicemia_alvo_max > glicemia_alvo_min)
);


