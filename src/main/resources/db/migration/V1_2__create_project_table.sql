CREATE TABLE "projects"
(
    "id"           BIGSERIAL PRIMARY KEY,
    "name"         VARCHAR(255)                                       NOT NULL,
    "company_id"   BIGINT                                             NOT NULL,
    CONSTRAINT fk_project_companies
        FOREIGN KEY (company_id)
            REFERENCES "companies" (id),
    "is_deleted"   BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "is_verified"  BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);