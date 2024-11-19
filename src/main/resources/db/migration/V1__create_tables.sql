CREATE TABLE organization
(
    id          VARCHAR(255) PRIMARY KEY NOT NULL,
    name        VARCHAR(255)             NOT NULL UNIQUE,
    description VARCHAR(255),
    phone       VARCHAR(255)             NOT NULL,
    email       VARCHAR(255)             NOT NULL,
    address     VARCHAR(1000)            NOT NULL
);

CREATE TABLE organization_image
(
    id              VARCHAR(255) PRIMARY KEY NOT NULL,
    organization_id VARCHAR(255)             NOT NULL UNIQUE,
    full_size       BYTEA                    NOT NULL,
    thumbnail       BYTEA                    NOT NULL,
    CONSTRAINT organization_image_organization_id_fk FOREIGN KEY (organization_id) REFERENCES organization (id)
);
