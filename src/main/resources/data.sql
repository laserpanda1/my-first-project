INSERT INTO Ingredient (id, name, type) VALUES
('FLTO', 'Flour Tortilla', 'WRAP'),
('GRBF', 'Ground Beef', 'PROTEIN'),
('CHED', 'Cheddar', 'CHEESE');

-- Затем добавляем тестовый заказ
INSERT INTO Taco_Order (delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at)
VALUES ('Test User', '123 Main St', 'Anytown', 'CA', '12345', '1234123412341234', '12/24', '123', CURRENT_TIMESTAMP);

-- Добавляем тако, связанное с заказом
INSERT INTO Taco (name, created_at, taco_order_id)
VALUES ('Test Taco', CURRENT_TIMESTAMP, 1);

-- Связываем ингредиенты с тако
INSERT INTO Ingredient_Ref (taco_id, ingredient_id) VALUES (1, 'FLTO'), (1, 'GRBF'), (1, 'CHED');