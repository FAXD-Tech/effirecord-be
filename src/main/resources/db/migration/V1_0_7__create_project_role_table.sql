CREATE TABLE "roles"
(
    "id"           BIGSERIAL PRIMARY KEY,
    "entity_type"  VARCHAR(30)  NOT NULL, -- project or company
    "entity_id"    BIGINT       NOT NULL, -- project id ot company id
    "name"         VARCHAR(30)  NOT NULL,
    "description"  VARCHAR(30)  NOT NULL,
    "created_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "updated_time" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);