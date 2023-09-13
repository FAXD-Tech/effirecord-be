CREATE TABLE "employees"
(
    "id"           BIGSERIAL PRIMARY KEY,
    "phone"        VARCHAR(255) UNIQUE                                NOT NULL,
    "name"         VARCHAR(255)                                       NOT NULL,
    "password"     VARCHAR(64)                                        NOT NULL,
    "rate"         NUMERIC,
    "is_verified"  BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "is_deleted"   BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);