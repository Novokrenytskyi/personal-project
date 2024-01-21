ALTER TABLE product
    ALTER COLUMN id RESTART WITH 1;

INSERT INTO product (id, description, name, price, product_type)
VALUES (1, 'Description 1', 'Product 1', 10.99, 'FIRST_TYPE'),
       (2, 'Description 2', 'Product 2', 20.99, 'SECOND_TYPE'),
       (3, 'Description 3', 'Product 3', 30.99, 'THIRD_TYPE'),
       (4, 'Description 4', 'Product 4', 40.99, 'FIRST_TYPE'),
       (5, 'Description 5', 'Product 5', 50.99, 'SECOND_TYPE'),
       (6, 'Description 6', 'Product 6', 60.99, 'THIRD_TYPE'),
       (7, 'Description 7', 'Product 7', 70.99, 'FIRST_TYPE'),
       (8, 'Description 8', 'Product 8', 80.99, 'SECOND_TYPE'),
       (9, 'Description 9', 'Product 9', 90.99, 'THIRD_TYPE'),
       (10, 'Description 10', 'Product 10', 100.99, 'FIRST_TYPE'),
       (11, 'Description 11', 'Product 11', 110.99, 'SECOND_TYPE'),
       (12, 'Description 12', 'Product 12', 120.99, 'THIRD_TYPE'),
       (13, 'Description 13', 'Product 13', 130.99, 'FIRST_TYPE'),
       (14, 'Description 14', 'Product 14', 140.99, 'SECOND_TYPE'),
       (15, 'Description 15', 'Product 15', 150.99, 'THIRD_TYPE'),
       (16, 'Description 16', 'Product 16', 160.99, 'FIRST_TYPE'),
       (17, 'Description 17', 'Product 17', 170.99, 'SECOND_TYPE'),
       (18, 'Description 18', 'Product 18', 180.99, 'THIRD_TYPE'),
       (19, 'Description 19', 'Product 19', 190.99, 'FIRST_TYPE'),
       (20, 'Description 20', 'Product 20', 200.99, 'SECOND_TYPE'),
       (21, 'Description 21', 'Product 21', 210.99, 'THIRD_TYPE'),
       (22, 'Description 22', 'Product 22', 220.99, 'FIRST_TYPE'),
       (23, 'Description 23', 'Product 23', 230.99, 'SECOND_TYPE'),
       (24, 'Description 24', 'Product 24', 240.99, 'THIRD_TYPE'),
       (25, 'Description 25', 'Product 25', 250.99, 'FIRST_TYPE'),
       (26, 'Description 26', 'Product 26', 260.99, 'SECOND_TYPE'),
       (27, 'Description 27', 'Product 27', 270.99, 'THIRD_TYPE'),
       (28, 'Description 28', 'Product 28', 280.99, 'FIRST_TYPE'),
       (29, 'Description 29', 'Product 29', 290.99, 'SECOND_TYPE'),
       (30, 'Description 30', 'Product 30', 300.99, 'THIRD_TYPE');

ALTER TABLE product
    ALTER COLUMN id RESTART WITH 30;



INSERT INTO users (id, birth_date, email, first_name, gender, last_name, password, role, created_at, last_modified_at)
VALUES (1, '2000-01-01', 'user1@example.com', 'User1', 'MALE', 'Last1', 'password1', 'USER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       (2, '2000-02-02', 'user2@example.com', 'User2', 'FEMALE', 'Last2', 'password2', 'USER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       (3, '2000-03-03', 'user3@example.com', 'User3', 'MALE', 'Last3', 'password3', 'USER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       (4, '2000-04-04', 'user4@example.com', 'User4', 'FEMALE', 'Last4', 'password4', 'USER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       (5, '2000-05-05', 'user5@example.com', 'User5', 'MALE', 'Last5', 'password5', 'USER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       (6, '2000-06-06', 'user6@example.com', 'User6', 'FEMALE', 'Last6', 'password6', 'USER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       (7, '2000-07-07', 'user7@example.com', 'User7', 'MALE', 'Last7', 'password7', 'USER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       (8, '2000-08-08', 'user8@example.com', 'User8', 'FEMALE', 'Last8', 'password8', 'USER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       (9, '2000-09-09', 'user9@example.com', 'User9', 'MALE', 'Last9', 'password9', 'USER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       (10, '2000-10-10', 'user10@example.com', 'User10', 'FEMALE', 'Last10', 'password10', 'USER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

ALTER TABLE users
    ALTER COLUMN id RESTART WITH 11;


INSERT INTO shopping_cart (id, user_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10);

ALTER TABLE shopping_cart
    ALTER COLUMN id RESTART WITH 11;

INSERT INTO shopping_cart_products (shopping_cart_id, product_id, quantity)
SELECT sc.id AS shopping_cart_id,
       p.id  AS product_id,
       v.quantity
FROM shopping_cart sc
         CROSS JOIN (VALUES (1, 3, 3),
                            (1, 2, 2),
                            (1, 1, 1)) AS v(shopping_cart_id, product_id, quantity)
         JOIN product p ON p.id = v.product_id;


