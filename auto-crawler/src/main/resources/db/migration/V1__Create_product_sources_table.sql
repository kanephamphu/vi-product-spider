CREATE TABLE product_sources (
    id VARCHAR(255) PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    crawlerProvider VARCHAR(100),
    systemId VARCHAR(255)
);