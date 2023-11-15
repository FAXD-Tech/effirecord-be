CREATE TABLE "user_project_authority"
(
    "user_id"      BIGINT,
    "project_id"   BIGINT,
    "role_id"      BIGINT,
    "authority_id" BIGINT,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT pk_user_authority_new PRIMARY KEY (user_id, project_id, role_id, authority_id),
    CONSTRAINT fk_user_user_authority FOREIGN KEY (user_id) REFERENCES "employees" (id),
    CONSTRAINT fk_authority_user_authority_new FOREIGN KEY (role_id) REFERENCES "roles" (id),
    CONSTRAINT fk_authority_user_authority_new FOREIGN KEY (authority_id) REFERENCES "authority" (id),
    CONSTRAINT fk_project_user_authority_new FOREIGN KEY (project_id) REFERENCES "projects" (id)
);