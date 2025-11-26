CREATE TYPE employee_status AS ENUM ('ACTIVE', 'INACTIVE');

-- 2) Tabel employee
CREATE TABLE employee (
  id UUID PRIMARY KEY,
  full_name VARCHAR(100) NOT NULL,
  nick_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  job_title VARCHAR(100),
  salary NUMERIC(15, 2),
  status employee_status DEFAULT 'ACTIVE',
  created_at  TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
  updated_at  TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
  deleted_at  TIMESTAMPTZ NULL
);

