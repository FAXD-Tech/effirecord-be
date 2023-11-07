DROP TABLE work_hours;
CREATE TABLE work_hours
(
    id              SERIAL PRIMARY KEY,
    project_id      BIGINT        NOT NULL,
    employee_id     INT           NOT NULL,
    attendance_date DATE          NOT NULL,
    hours_worked    DECIMAL(5, 2) NOT NULL,
    comment         TEXT,
    created_by      BIGINT        NOT NULL,
    updated_by      BIGINT        NOT NULL,
    is_deleted    BOOLEAN DEFAULT FALSE NOT NULL,
    created_time  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_time  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_work_hours_employee FOREIGN KEY (employee_id) REFERENCES employees (id),
    CONSTRAINT fk_work_hours_project FOREIGN KEY (project_id) REFERENCES projects (id),
    CONSTRAINT fk_work_hours_created_by FOREIGN KEY (created_by) REFERENCES employees (id),
    CONSTRAINT fk_work_hours_updated_by FOREIGN KEY (updated_by) REFERENCES employees (id)
);