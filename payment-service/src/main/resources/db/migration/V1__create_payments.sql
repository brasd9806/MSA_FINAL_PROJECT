CREATE TABLE IF NOT EXISTS payments (
    id         BIGSERIAL    NOT NULL,
    order_id   BIGINT       NOT NULL,
    amount     BIGINT       NOT NULL,
    status     VARCHAR(20)  NOT NULL DEFAULT 'COMPLETED',
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
