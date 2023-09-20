CREATE TABLE "employees"
(
    "id"                 BIGSERIAL PRIMARY KEY,
    "phone"              VARCHAR(255) UNIQUE                                NOT NULL,
    "name"               VARCHAR(255)                                       NOT NULL,
    "password"           VARCHAR(64)                                        NOT NULL,
    "current_project_id" BIGINT,
    CONSTRAINT fk_project_companies
        FOREIGN KEY (current_project_id)
            REFERENCES "projects" (id),
    "rate"               NUMERIC,
    "is_verified"        BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "is_deleted"         BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "created_time"       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time"       TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);