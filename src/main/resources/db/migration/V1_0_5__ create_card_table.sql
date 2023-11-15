CREATE TABLE "cards"
(
    "id"           BIGSERIAL PRIMARY KEY,
    alias          varchar(255)                                       NOT NULL,
    "employee_id"  BIGINT                                             NOT NULL,
    CONSTRAINT fk_user_user_authority
        FOREIGN KEY (employee_id)
            REFERENCES "employees" (id),
    "bank_name"    varchar(255)                                       NOT NULL,
    "province"     varchar(255)                                       NOT NULL,
    "city"         varchar(255)                                       NOT NULL,
    "bank_outlet"  varchar(255)                                       NOT NULL,
    "card_number"  VARCHAR(255) UNIQUE                                NOT NULL,
    "card_holder"  VARCHAR(255)                                       NOT NULL,
    "is_deleted"   BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);