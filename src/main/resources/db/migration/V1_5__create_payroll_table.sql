CREATE TABLE "payroll"
(
    "id"          BIGSERIAL PRIMARY KEY,
    "employee_id" BIGINT,
    CONSTRAINT fk_employee_payroll
        FOREIGN KEY (employee_id)
            REFERENCES "employees" (id),
    "paid_money"  NUMERIC NOT NULL,
    "paid_date"   DATE NOT NULL,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);