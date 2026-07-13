CREATE TABLE IF NOT EXISTS product_options (
    id               BIGSERIAL    NOT NULL,
    product_id       BIGINT       NOT NULL,
    name             VARCHAR(100) NOT NULL,
    additional_price NUMERIC      NOT NULL,
    stock            INTEGER      NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
