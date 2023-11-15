CREATE TABLE "payroll"
(
    "id"             BIGSERIAL PRIMARY KEY,
    "payee_id"       BIGINT                                             NOT NULl,
    CONSTRAINT fk_payee_id_payroll
        FOREIGN KEY (payee_id)
            REFERENCES "employees" (id),
    "paid_money"     NUMERIC                                            NOT NULL,
    "paid_date"      DATE                                               NOT NULL,
    "payment_method" VARCHAR(255),
    "comment"        VARCHAR(255),
    "payer_id"       BIGINT                                             NOT NULL,
    CONSTRAINT fk_employee_payer_payroll
        FOREIGN KEY (payer_id)
            REFERENCES "employees" (id),
    "card_id"        BIGINT,
    CONSTRAINT f_card_payroll
        FOREIGN KEY (card_id)
            REFERENCES "cards" (id),
    "is_deleted"     BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "created_time"   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time"   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);