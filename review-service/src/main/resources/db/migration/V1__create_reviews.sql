CREATE TABLE IF NOT EXISTS reviews (
    id          BIGSERIAL    NOT NULL,
    user_id     BIGINT       NOT NULL,
    product_id  BIGINT       NOT NULL,
    order_id    BIGINT       NOT NULL,
    rating      INT          NOT NULL CHECK (rating >= 1 AND rating <= 5),
    content     VARCHAR(500) NOT NULL,
    created_at  TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
