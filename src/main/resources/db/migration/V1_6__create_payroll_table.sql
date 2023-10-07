CREATE TABLE "payroll"
(
    "id"           BIGSERIAL PRIMARY KEY,
    "employee_id"  BIGINT,
    CONSTRAINT fk_employee_payroll
        FOREIGN KEY (employee_id)
            REFERENCES "employees" (id),
    "paid_money"   NUMERIC                                            NOT NULL,
    "paid_date"    DATE                                               NOT NULL,
    "payment_method" VARCHAR(255),
    "comment" VARCHAR(255),
    "payer_id" BIGINT,
    CONSTRAINT fk_employee_payer_payroll
        FOREIGN KEY (employee_id)
            REFERENCES "employees" (id),
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);