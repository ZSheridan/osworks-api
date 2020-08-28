CREATE TABLE service_order (
    id BIGINT NOT NULL AUTO_INCREMENT,
    client_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    opening_date DATETIME NOT NULL,
    finishing_date DATETIME,

    PRIMARY KEY (id)
);

ALTER TABLE service_order ADD CONSTRAINT fk_service_order_client
FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE;