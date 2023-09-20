CREATE TABLE "cards"
(
    "id"           BIGSERIAL PRIMARY KEY,
    "employee_id"  BIGINT                                             NOT NULL,
    CONSTRAINT fk_user_user_authority
        FOREIGN KEY (employee_id)
            REFERENCES "employees" (id),
    "card_number"  VARCHAR(255) UNIQUE                                NOT NULL,
    "card_holder"  VARCHAR(255)                                       NOT NULL,
    "open_address" VARCHAR(255)                                       NOT NULL,
    "is_deleted"   BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);