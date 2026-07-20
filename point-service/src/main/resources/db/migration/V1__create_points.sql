CREATE TABLE IF NOT EXISTS point_histories (
    id          BIGSERIAL    NOT NULL,
    user_id     BIGINT       NOT NULL,
    amount      BIGINT       NOT NULL,
    type        VARCHAR(20)  NOT NULL,
    description VARCHAR(200),
    created_at  TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
