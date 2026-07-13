CREATE TABLE IF NOT EXISTS orders (
    id               BIGSERIAL       NOT NULL,
    user_id          BIGINT          NOT NULL,
    address_id       BIGINT          NOT NULL,
    total_price      BIGINT          NOT NULL,
    status           VARCHAR(20)     NOT NULL DEFAULT 'PENDING',
    created_at       TIMESTAMP(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS order_items (
    id           BIGSERIAL    NOT NULL,
    order_id     BIGINT       NOT NULL,
    product_id   BIGINT       NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    quantity     INTEGER      NOT NULL,
    price        BIGINT       NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);
