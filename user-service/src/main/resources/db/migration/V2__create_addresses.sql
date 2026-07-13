CREATE TABLE IF NOT EXISTS user_addresses (
    id         BIGSERIAL       NOT NULL,
    user_id    BIGINT          NOT NULL,
    address    VARCHAR(500)    NOT NULL,
    is_default BOOLEAN         NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
