-- Удаляем все данные
DELETE FROM Ingredient_Ref;
DELETE FROM Taco;
DELETE FROM Taco_Order;
DELETE FROM Ingredient;

-- Вставляем заказ (без ID)
INSERT INTO Taco_Order (delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at)
VALUES ('Test User', '123 Main St', 'Anytown', 'CA', '12345', '1234123412341234', '12/24', '123', CURRENT_TIMESTAMP);

-- Вставляем тако, привязанное к последнему заказу
INSERT INTO Taco (name, created_at, taco_order_id)
SELECT 'Test Taco', CURRENT_TIMESTAMP, id FROM Taco_Order LIMIT 1;

-- Связываем ингредиенты
INSERT INTO Ingredient_Ref (taco_id, ingredient_id)
SELECT t.id, i.id FROM Taco t, Ingredient i
WHERE t.name = 'Test Taco' AND i.id IN ('FLTO', 'GRBF', 'CHED');