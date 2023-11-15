CREATE TABLE "role_authority"
(
    "role_id"  BIGINT,
    "authority_id" BIGINT,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT pk_user_role_authority PRIMARY KEY (role_id, authority_id),
    CONSTRAINT fk_role_role_authority FOREIGN KEY (role_id) REFERENCES "roles" (id),
    CONSTRAINT fk_authority_role_authority FOREIGN KEY (authority_id) REFERENCES "authority" (id)
);