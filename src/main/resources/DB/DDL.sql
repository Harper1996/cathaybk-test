CREATE TABLE product (
                         product_id VARCHAR(50) PRIMARY KEY,
                         name VARCHAR(100),
                         short_name VARCHAR(50),
                         data_grouping BOOLEAN
);

CREATE TABLE price (
                       price_id SERIAL PRIMARY KEY,
                       product_id VARCHAR(50),
                       date BIGINT,
                       price DECIMAL(10, 5),
                       FOREIGN KEY (product_id) REFERENCES product(product_id)
);

