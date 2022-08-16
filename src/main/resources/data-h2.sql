CREATE TABLE IF NOT EXISTS person (
    id           SERIAL PRIMARY KEY,
    first_name   VARCHAR NOT NULL,
    birth        DATE NOT NULL,
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP);

CREATE TABLE IF NOT EXISTS product (
    id                SERIAL PRIMARY KEY,
    description       VARCHAR(255) NOT NULL,
    validity          DATE,
    weight            FLOAT,
    date_created      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_updated      TIMESTAMP,
    creation_person_id INT NOT NULL,
    CONSTRAINT product_fk_person
        FOREIGN KEY (creation_person_id)
            REFERENCES person(id));

CREATE TABLE IF NOT EXISTS movement_type (
    id           SERIAL PRIMARY KEY,
    description  VARCHAR(50),
    date_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_updated TIMESTAMP,
    creation_person_id INT NOT NULL,
    CONSTRAINT product_fk_person
        FOREIGN KEY (creation_person_id)
            REFERENCES person(id));

CREATE TABLE IF NOT EXISTS movement (
    id                 SERIAL PRIMARY KEY,
    date_created       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    quantity           INT NOT NULL,
    product_id         INT NOT NULL,
    creation_person_id INT NOT NULL,
    movement_type_id   INT NOT NULL,
    CONSTRAINT movement_fk_product
        FOREIGN KEY (product_id)
            REFERENCES product(id),
    CONSTRAINT movement_fk_person
        FOREIGN KEY (creation_person_id)
            REFERENCES person(id),
    CONSTRAINT movement_fk_movement_type
        FOREIGN KEY (movement_type_id)
            REFERENCES movement_type(id)
);
