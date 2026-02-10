CREATE TABLE drink (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       price DECIMAL,
                       sugar BOOLEAN,
                       best_before DATE,
                       quantity INT
);


ALTER TABLE drink RENAME COLUMN sugar TO has_sugar;