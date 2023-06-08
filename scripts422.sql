CREATE TABLE users (
    id REAL PRIMARY KEY,
    name TEXT,
    age INT,
    driver_lic BOOLEAN,
    car_id REAL REFERENCES cars (id)
);

CREATE TABLE cars (
    id REAL PRIMARY KEY,
    brand CHAR(20),
    model CHAR(20)
)