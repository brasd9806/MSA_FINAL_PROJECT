CREATE TABLE IF NOT EXISTS cart_items (
    id            BIGSERIAL    NOT NULL,
    user_id       BIGINT       NOT NULL,
    product_id    BIGINT       NOT NULL,
    product_name  VARCHAR(255) NOT NULL,
    product_price BIGINT       NOT NULL,
    quantity      INT          NOT NULL DEFAULT 1,
    created_at    TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
