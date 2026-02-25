CREATE TABLE spirit
(
    SpiritID   INT          NOT NULL,
    Type       VARCHAR(255) NOT NULL,
    Name       VARCHAR(255) NOT NULL,
    APV        FLOAT,
    AgeInMonth INT,
    Price      FLOAT,
    PRIMARY KEY (SpiritID)
);

