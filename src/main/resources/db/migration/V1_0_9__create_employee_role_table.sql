CREATE TABLE "employee_role"
(
    "employee_id"      BIGINT,
    "role_id"      BIGINT,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT pk_employee_role PRIMARY KEY (employee_id, role_id),
    CONSTRAINT fk_employee_employee_role FOREIGN KEY (employee_id) REFERENCES "employees" (id),
    CONSTRAINT fk_role_employee_role FOREIGN KEY (role_id) REFERENCES "roles" (id)
);