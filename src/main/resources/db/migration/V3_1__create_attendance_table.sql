CREATE TABLE work_hours
(
    id              SERIAL PRIMARY KEY,
    employee_id     INT           NOT NULL,
    attendance_date DATE          NOT NULL,
    hours_worked    DECIMAL(5, 2) NOT NULL,
    comment         TEXT,
    FOREIGN KEY (employee_id) REFERENCES employees (id)
);