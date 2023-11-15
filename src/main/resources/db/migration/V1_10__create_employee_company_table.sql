CREATE TABLE "employee_company_authority"
(
    "employee_id"  BIGINT,
    "company_id"   BIGINT,
    "role_id"      BIGINT,
    "authority_id" BIGINT,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT pk_user_company_authority PRIMARY KEY (employee_id, company_id, authority_id),
    CONSTRAINT fk_user_company_authority FOREIGN KEY (employee_id) REFERENCES "employees" (id),
    CONSTRAINT fk_authority_user_company FOREIGN KEY (role_id) REFERENCES "roles" (id),
    CONSTRAINT fk_authority_user_company FOREIGN KEY (authority_id) REFERENCES "authority" (id),
    CONSTRAINT fk_companies_user_authority_new FOREIGN KEY (company_id) REFERENCES "companies" (id)
);