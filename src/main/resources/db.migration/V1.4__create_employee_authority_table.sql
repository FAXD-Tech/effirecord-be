CREATE TABLE "user_authority"
(
    "user_id"      BIGINT,
    CONSTRAINT fk_user_user_authority
        FOREIGN KEY (user_id)
            REFERENCES "employees" (id),
    "authority_id" BIGINT,
    CONSTRAINT fk_authority_user_authority
        FOREIGN KEY (authority_id)
            REFERENCES "authority" (id),
    PRIMARY KEY (user_id, authority_id),
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);