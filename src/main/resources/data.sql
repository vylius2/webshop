DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS cart_product;

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

INSERT INTO "user" (username, password)
VALUES ('admin', 'admin');
INSERT INTO role (name)
VALUES ('ADMIN');
INSERT INTO product(name, price, description, picture_path)
VALUES('Pomidoras', 2.99, 'Raudonas pomidoras', 'null');
INSERT INTO comment (content, product_id, user_id)
VALUES('bumshakalaka-yeyo', 1, 1);