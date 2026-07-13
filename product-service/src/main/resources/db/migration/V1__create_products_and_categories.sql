CREATE TABLE IF NOT EXISTS categories (
    id         BIGSERIAL       NOT NULL,
    name       VARCHAR(100)    NOT NULL UNIQUE,
    parent_id  BIGINT,
    created_at TIMESTAMP(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (parent_id) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS products (
    id             BIGSERIAL       NOT NULL,
    name           VARCHAR(255)    NOT NULL,
    price          BIGINT          NOT NULL,
    stock          INTEGER         NOT NULL,
    description    VARCHAR(1000),
    category_id    BIGINT          NOT NULL,
    status         VARCHAR(20)     NOT NULL DEFAULT 'ON_SALE',
    discount_rate  INTEGER,
    discount_price BIGINT,
    created_at     TIMESTAMP(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP(6)    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);
