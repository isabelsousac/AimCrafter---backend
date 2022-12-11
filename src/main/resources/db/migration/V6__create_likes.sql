CREATE TABLE likes(
    userId integer REFERENCES users(id),
    craftId integer REFERENCES crafts(id)
);

CREATE UNIQUE INDEX ON likes(userId, craftId);
