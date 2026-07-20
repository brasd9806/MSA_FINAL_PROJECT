CREATE TABLE IF NOT EXISTS payments (
    id               BIGSERIAL    NOT NULL,
    order_id         BIGINT       NOT NULL,
    total_amount     BIGINT       NOT NULL,
    cancelled_amount BIGINT       NOT NULL DEFAULT 0,
    created_at       TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS payment_logs (
    id          BIGSERIAL    NOT NULL,
    payment_id  BIGINT       NOT NULL,
    action      VARCHAR(30)  NOT NULL,
    amount      BIGINT       NOT NULL,
    description VARCHAR(200),
    created_at  TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (payment_id) REFERENCES payments(id)
);
