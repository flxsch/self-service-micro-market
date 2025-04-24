-- Users Table
CREATE TABLE users (
                       id varchar(255) PRIMARY KEY,
                       first_name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       birth_date DATE,
                       language VARCHAR(50),
                       created_by_user_id VARCHAR(255),
                       updated_by_user_id VARCHAR(255),
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);

-- Product Table
CREATE TABLE product (
                         id varchar(255) PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         category VARCHAR(100) NOT NULL,
                         stock INT NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         barcode VARCHAR(255) NOT NULL,
                         created_by_user_id VARCHAR(255),
                         updated_by_user_id VARCHAR(255),
                         created_at TIMESTAMP,
                         updated_at TIMESTAMP
);

-- Orders Table
CREATE TABLE orders (
                        id varchar(255) PRIMARY KEY,
                        user_id varchar(255),
                        payment_id VARCHAR(255),
                        created_by_user_id VARCHAR(255),
                        updated_by_user_id VARCHAR(255),
                        created_at TIMESTAMP,
                        updated_at TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

-- OrderProduct Table
CREATE TABLE order_product (
                               id varchar(255) PRIMARY KEY,
                               order_id varchar(255) NOT NULL,
                               product_id varchar(255) NOT NULL,
                               amount INT NOT NULL,
                               FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                               FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);
