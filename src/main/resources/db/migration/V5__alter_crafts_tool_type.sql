ALTER TABLE crafts
    ALTER COLUMN tools TYPE text[] USING tools::text[]