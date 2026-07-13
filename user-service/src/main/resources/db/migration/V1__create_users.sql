CREATE TABLE IF NOT EXISTS users (
    id            BIGSERIAL       NOT NULL,
    user_id       VARCHAR(255)    NOT NULL UNIQUE,
    user_nm       VARCHAR(255)    NOT NULL,
    phone_number  VARCHAR(20)     NOT NULL,
    password_hash VARCHAR(255)    NOT NULL,
    role          VARCHAR(20)     NOT NULL DEFAULT 'USER',
    use_yn        VARCHAR(1)      NOT NULL DEFAULT 'Y',
    created_at    TIMESTAMP(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
