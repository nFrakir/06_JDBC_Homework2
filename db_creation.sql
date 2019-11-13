CREATE DATABASE eshop;
SET global time_zone="+2:0";

CREATE TABLE goods (
	id INT NOT NULL AUTO_INCREMENT, 
    PRIMARY KEY (id),
    title VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL);

CREATE TABLE clients (
	id INT NOT NULL AUTO_INCREMENT, 
    PRIMARY KEY (id),
    login VARCHAR(255) NOT NULL,
    phone VARCHAR(10));
    
CREATE TABLE orders (
	id INT NOT NULL AUTO_INCREMENT, 
    PRIMARY KEY (id),
    order_date DATETIME DEFAULT now(),
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE);
    
CREATE TABLE goods_orders (
	id INT NOT NULL AUTO_INCREMENT, 
    PRIMARY KEY (id),
    order_id INT NOT NULL,
    goods_id INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
	FOREIGN KEY (goods_id) REFERENCES goods(id) ON DELETE CASCADE);
    
INSERT INTO goods VALUES
	(DEFAULT, "Tea-pot", 22.0),
    (DEFAULT, "Frying pan", 570.0),
    (DEFAULT, "Blanket", 100.2),
    (DEFAULT, "Cap", 5.5),
    (DEFAULT, "Coat", 150);
    
INSERT INTO clients VALUES
	(DEFAULT, "Hensen", "2128506"),
    (DEFAULT, "Adel", "2121010"),
    (DEFAULT, "Dikkens", "3232332");
    
INSERT INTO orders VALUES
	(DEFAULT, DEFAULT, 1),
    (DEFAULT, DEFAULT, 2);
    
INSERT INTO goods_orders VALUES
	(DEFAULT, 1, 1),
	(DEFAULT, 1, 2),
	(DEFAULT, 1, 3),
	(DEFAULT, 2, 1),
	(DEFAULT, 2, 3),
	(DEFAULT, 2, 4),
	(DEFAULT, 2, 5);

USE `eshop`;
CREATE  OR REPLACE VIEW `orders_info` AS
SELECT
	order_id,
    order_date,
    clients.id AS client_id,
    clients.login,
    clients.phone,
	SUM(goods.price) AS total
FROM goods_orders 
LEFT JOIN goods ON goods_orders.goods_id = goods.id
LEFT JOIN orders ON goods_orders.order_id = orders.id
LEFT JOIN clients ON orders.client_id = clients.id
GROUP BY goods_orders.order_id;

USE `eshop`;
CREATE  OR REPLACE VIEW `single_order_info` AS
SELECT order_id, goods_id, title, price FROM goods_orders 
LEFT JOIN goods ON goods_orders.goods_id = goods.id;
    
