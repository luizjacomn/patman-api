CREATE TABLE IF NOT EXISTS patients (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    cpf TEXT NOT NULL,
    birth_date DATE NOT NULL,
    email TEXT,
    phone TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_patients_name ON patients(name);

CREATE UNIQUE INDEX IF NOT EXISTS idx_patients_cpf ON patients(cpf);
