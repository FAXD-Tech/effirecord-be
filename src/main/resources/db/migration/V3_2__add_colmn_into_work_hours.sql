ALTER TABLE work_hours
    ADD COLUMN project_id BIGINT NOT NULL;
ALTER TABLE work_hours
    ADD COLUMN created_by BIGINT NOT NULL;
ALTER TABLE work_hours
    ADD COLUMN updated_by BIGINT NOT NULL;
ALTER TABLE work_hours
    ADD COLUMN "is_deleted" BOOLEAN DEFAULT FALSE NOT NULL;
ALTER TABLE work_hours
    ADD COLUMN "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE work_hours
    ADD COLUMN "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE work_hours
    ADD CONSTRAINT fk_work_hours_project
        FOREIGN KEY (project_id) REFERENCES "projects" (id);
ALTER TABLE work_hours
    ADD CONSTRAINT fk_work_hours_created_by
        FOREIGN KEY (created_by) REFERENCES "employees" (id);
ALTER TABLE work_hours
    ADD CONSTRAINT fk_work_hours_updated_by
        FOREIGN KEY (updated_by) REFERENCES "employees" (id);