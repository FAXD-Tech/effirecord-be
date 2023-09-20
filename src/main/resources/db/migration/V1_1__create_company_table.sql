CREATE TABLE "companies"
(
    "id"           BIGSERIAL PRIMARY KEY,
    "name"         VARCHAR(255)                                       NOT NULL,
    "is_deleted"   BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "is_verified"  BOOLEAN                  DEFAULT FALSE             NOT NULL,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);