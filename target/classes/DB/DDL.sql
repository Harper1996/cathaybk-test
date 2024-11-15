DELIMITER //

CREATE PROCEDURE create_tables()
BEGIN
    CREATE TABLE IF NOT EXISTS product (
        id VARCHAR(20) PRIMARY KEY,
        name VARCHAR(255),
        shortName VARCHAR(255),
        dataGrouping BOOLEAN
    );

    CREATE TABLE IF NOT EXISTS price (
        product_id VARCHAR(20),
        date BIGINT,
        price DECIMAL(10, 5),
        PRIMARY KEY (product_id, date),
        FOREIGN KEY (product_id) REFERENCES product(id)
    );
END//

DELIMITER ;
