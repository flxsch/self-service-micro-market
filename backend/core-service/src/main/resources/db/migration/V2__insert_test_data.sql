INSERT INTO users (id, first_name, last_name, email, birth_date, language, created_at, created_by)
VALUES
    ('0', 'Admin', 'Admin', 'Admin@Admin.com', null, 'EN', NOW(), '0'),
    ('1', 'Alice', 'Smith', 'alice@example.com', TO_DATE('1990-01-15','YYYY-MM-DD'), 'EN', NOW(), '0'),
    ('2', 'Bob', 'Johnson', 'bob@example.com', TO_DATE('1988-07-23','YYYY-MM-DD'), 'EN', NOW(), '0'),
    ('3', 'Charlie', 'Lee', 'charlie@example.com', TO_DATE('1992-03-10','YYYY-MM-DD'), 'EN', NOW(), '0'),
    ('4', 'Diana', 'Nguyen', 'diana@example.com', TO_DATE('1995-12-01','YYYY-MM-DD'), 'EN', NOW(), '0'),
    ('5', 'Eve', 'Martinez', 'eve@example.com', TO_DATE('1991-06-18','YYYY-MM-DD'), 'EN', NOW(), '0');

INSERT INTO product (id, name, category, stock, price, barcode, created_at, created_by)
VALUES
    ('6', 'Spaghetti Bolognese', 'food', 120, 8.99, 'FOOD-001', NOW(), '0'),
    ('7', 'Grilled Chicken Breast', 'food', 130, 10.99, 'FOOD-002', NOW(), '0'),
    ('8', 'Veggie Burger', 'food', 110, 7.49, 'FOOD-003', NOW(), '0'),
    ('9', 'Beef Lasagna', 'food', 140, 9.99, 'FOOD-004', NOW(), '0'),
    ('10', 'Chicken Caesar Salad', 'food', 150, 6.99, 'FOOD-005', NOW(), '0'),
    ('11', 'Tuna Sandwich', 'food', 160, 5.99, 'FOOD-006', NOW(), '0'),
    ('12', 'Penne Alfredo', 'food', 170, 8.49, 'FOOD-007', NOW(), '0'),
    ('13', 'Coca-Cola 500ml', 'drinks', 180, 1.49, 'DRINK-001', NOW(), '0'),
    ('14', 'Orange Juice 1L', 'drinks', 190, 2.99, 'DRINK-002', NOW(), '0'),
    ('15', 'Bottled Water 1L', 'drinks', 200, 0.99, 'DRINK-003', NOW(), '0'),
    ('16', 'Red Bull 250ml', 'drinks', 210, 2.49, 'DRINK-004', NOW(), '0'),
    ('17', 'Iced Coffee Latte', 'drinks', 220, 3.29, 'DRINK-005', NOW(), '0'),
    ('18', 'Green Tea Bottle', 'drinks', 230, 2.19, 'DRINK-006', NOW(), '0'),
    ('19', 'Sparkling Water', 'drinks', 240, 1.79, 'DRINK-007', NOW(), '0'),
    ('20', 'Potato Chips (Salted)', 'snacks', 250, 1.99, 'SNACK-001', NOW(), '0'),
    ('21', 'Chocolate Bar', 'snacks', 260, 1.49, 'SNACK-002', NOW(), '0'),
    ('22', 'Granola Bar', 'snacks', 270, 1.29, 'SNACK-003', NOW(), '0'),
    ('23', 'Trail Mix Pack', 'snacks', 280, 2.59, 'SNACK-004', NOW(), '0'),
    ('24', 'Peanut Butter Cookies', 'snacks', 290, 2.99, 'SNACK-005', NOW(), '0'),
    ('25', 'Cheddar Cheese Crackers', 'snacks', 300, 1.89, 'SNACK-006', NOW(), '0');

INSERT INTO orders (id, user_id, payment_id, created_at, created_by)
VALUES
    ('26', '1', 'PAY-001', NOW(), '0'),
    ('27', '1', 'PAY-002', NOW(), '0'),
    ('28', '2', 'PAY-003', NOW(), '0'),
    ('29', '2', 'PAY-004', NOW(), '0'),
    ('30', '3', 'PAY-005', NOW(), '0'),
    ('31', '3', 'PAY-006', NOW(), '0'),
    ('32', '4', 'PAY-007', NOW(), '0'),
    ('33', '4', 'PAY-008', NOW(), '0'),
    ('34', '5', 'PAY-009', NOW(), '0'),
    ('35', '5', 'PAY-010', NOW(), '0');

INSERT INTO order_product (id, order_id, product_id, amount)
VALUES
    ('36', '26', '6', 2),
    ('37', '26', '20', 1),
    ('38', '27', '8', 4),
    ('39', '28', '15', 1),
    ('40', '28', '13', 2),
    ('41', '29', '11', 3),
    ('42', '30', '7', 1),
    ('43', '31', '16', 2),
    ('44', '32', '14', 1),
    ('45', '33', '19', 5),
    ('46', '33', '9', 1),
    ('47', '34', '17', 2),
    ('48', '35', '23', 1),
    ('49', '35', '21', 2),
    ('50', '30', '18', 1),
    ('51', '31', '12', 2),
    ('52', '27', '22', 1),
    ('53', '32', '10', 2),
    ('54', '34', '24', 1),
    ('55', '35', '25', 3);