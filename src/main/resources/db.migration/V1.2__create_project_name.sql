CREATE TABLE "projects"
(
    "id"           BIGSERIAL PRIMARY KEY,
    "address"      VARCHAR(255) UNIQUE                                NOT NULL,
    "name"         VARCHAR(255)                                       NOT NULL,
    "is_deleted"   BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);