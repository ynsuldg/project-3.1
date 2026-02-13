CREATE TABLE food (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255),
                      has_lactose BOOLEAN,
                      has_seafood BOOLEAN,
                      barcode VARCHAR(255),
                      price DOUBLE PRECISION NOT NULL CHECK (price >= 0),
                      best_before DATE,
                      quantity INT
);