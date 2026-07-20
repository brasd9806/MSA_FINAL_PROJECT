CREATE TABLE IF NOT EXISTS wishlists (
    id         BIGSERIAL    NOT NULL,
    user_id    BIGINT       NOT NULL,
    product_id BIGINT       NOT NULL,
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    UNIQUE (user_id, product_id)
);
