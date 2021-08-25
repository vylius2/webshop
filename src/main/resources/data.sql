DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS role CASCADE;
DROP TABLE IF EXISTS user_role CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS cart CASCADE;
DROP TABLE IF EXISTS cart_product CASCADE;

CREATE TABLE "user"
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    username   VARCHAR(255) UNIQUE   NOT NULL,
    password   VARCHAR(255)          NOT NULL,
    created    TIMESTAMP             NOT NULL DEFAULT NOW()
);

CREATE TABLE role
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) UNIQUE   NOT NULL
);

CREATE TABLE user_role
(
    user_id BIGINT REFERENCES "user" (id),
    role_id BIGINT REFERENCES role (id)
);

CREATE TABLE product
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(55) NOT NULL,
    price NUMERIC(10, 2) DEFAULT 0.00,
    description TEXT,
    picture_path VARCHAR(255)
);

CREATE TABLE comment
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    content TEXT NOT NULL,
    product_id BIGINT NOT NULL REFERENCES product(id),
    user_id BIGINT NOT NULL REFERENCES "user"(id),
    created TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE cart
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT NOW(),
    user_id BIGINT NOT NULL REFERENCES "user"(id)
);

CREATE TABLE cart_product
(
    cart_id BIGINT NOT NULL REFERENCES cart(id),
    product_id BIGINT NOT NULL REFERENCES product(id)
);

INSERT INTO "user" (id, username, password)
VALUES (1, 'vilius2', '{bcrypt}$2a$10$lIQOAXhMFdXahaKZutZggefbXnBqOaAGxozJGTRGlLnQnmCmTFGY6');
INSERT INTO role (id, name)
VALUES (1, 'ADMIN'),
       (2, 'USER');
INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (1, 2);
INSERT INTO product (id, name, price, description, picture_path)
VALUES (1, 'Pomidoras', 1, 'Raudonas pomidoras', 'C:/asd/petras'),
       (2, 'Ryziai', 2, 'Energingi', 'C:/ne/taip');