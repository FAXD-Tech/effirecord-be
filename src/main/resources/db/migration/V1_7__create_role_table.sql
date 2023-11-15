CREATE TABLE "roles"
(
    "id"           BIGSERIAL PRIMARY KEY,
    "name"         VARCHAR(30)                                        NOT NULL,
    "description"  VARCHAR(30)                                        NOT NULL,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);