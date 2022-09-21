CREATE TABLE IF NOT EXISTS person (
    id           SERIAL PRIMARY KEY,
    first_name   VARCHAR NOT NULL,
    birth        DATE NOT NULL);

CREATE TABLE IF NOT EXISTS product (
    id                  SERIAL PRIMARY KEY,
    description         VARCHAR(255) NOT NULL,
    unit_measurement    VARCHAR(255),
    date_creation       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_updated        TIMESTAMP,
    person_creation_id  INT NOT NULL,

    CONSTRAINT product_fk_person
        FOREIGN KEY (person_creation_id)
            REFERENCES person(id));

CREATE TABLE IF NOT EXISTS movement_type (
    id                      SERIAL PRIMARY KEY,
    description             VARCHAR(50) NOT NULL,
    date_creation           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_updated            TIMESTAMP,
    status                  bit,
    person_creation_id      INT NOT NULL,

    CONSTRAINT movement_type_fk_person
        FOREIGN KEY (person_creation_id)
            REFERENCES person(id));

CREATE TABLE IF NOT EXISTS movement (
    id                   SERIAL PRIMARY KEY,
    date_creation        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quantity             INT NOT NULL,
    product_id           INT NOT NULL,
    person_creation_id   INT NOT NULL,
    movement_type_id     INT NOT NULL,

    CONSTRAINT movement_fk_product
        FOREIGN KEY (product_id)
            REFERENCES product(id),
    CONSTRAINT movement_fk_person
        FOREIGN KEY (creation_person_id)
            REFERENCES person(id),
    CONSTRAINT movement_fk_movement_type
        FOREIGN KEY (movement_type_id)
            REFERENCES movement_type(id));
