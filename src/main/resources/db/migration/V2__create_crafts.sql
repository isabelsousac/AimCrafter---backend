CREATE TABLE crafts(
    id bigserial PRIMARY KEY,
    title text,
    userId integer,
    tools text,
    description text DEFAULT NULL,
    howLong time DEFAULT NULL,
    difficultyLevel integer,
    createdAt timestamp NOT NULL DEFAULT now(),
    updatedAt timestamp NOT NULL DEFAULT now()
);