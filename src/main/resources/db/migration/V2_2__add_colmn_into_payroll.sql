ALTER TABLE payroll DROP COLUMN employee_id;
ALTER TABLE payroll ADD COLUMN "payee_id"  BIGINT;
ALTER TABLE payroll ADD CONSTRAINT fk_payee_id_payroll
        FOREIGN KEY (payee_id)
            REFERENCES "employees" (id);