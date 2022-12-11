CREATE TABLE users (
    id bigserial PRIMARY KEY,
    username text,
    firstName text,
    lastName text,
    email text,
    passwordDigest character varying,
    createdAt timestamp NOT NULL DEFAULT now(),
    updatedAt timestamp NOT NULL DEFAULT now()
);

CREATE UNIQUE INDEX ON users(email);