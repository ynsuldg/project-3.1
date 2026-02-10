CREATE TABLE food (
                      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      lactose BOOLEAN,
                      seafood BOOLEAN,
                      best_before DATE,
                      quantity INT
);

ALTER TABLE food RENAME COLUMN lactose TO has_lactose;
ALTER TABLE food RENAME COLUMN seafood TO has_seafood;

