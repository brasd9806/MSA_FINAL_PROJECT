CREATE TABLE IF NOT EXISTS coupons (
    id              BIGSERIAL    NOT NULL,
    code            VARCHAR(50)  NOT NULL UNIQUE,
    name            VARCHAR(100) NOT NULL,
    discount_rate   INT          NOT NULL DEFAULT 0,
    discount_amount BIGINT       NOT NULL DEFAULT 0,
    min_order_price BIGINT       NOT NULL DEFAULT 0,
    max_use_count   INT          NOT NULL DEFAULT 1,
    current_use_count INT        NOT NULL DEFAULT 0,
    expired_at      TIMESTAMP(6) NOT NULL,
    created_at      TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_coupons (
    id          BIGSERIAL    NOT NULL,
    user_id     BIGINT       NOT NULL,
    coupon_id   BIGINT       NOT NULL,
    used        BOOLEAN      NOT NULL DEFAULT FALSE,
    issued_at   TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    used_at     TIMESTAMP(6),
    PRIMARY KEY (id)
);
