
-- insert paramaters
INSERT INTO `parameters`(`key_code`, `value`) VALUES ('discount_for_above_3carton_per', 10);
INSERT INTO `parameters`(`key_code`, `value`) VALUES ('single_unit_price_increase_rate', 1.3);

-- insert item
INSERT INTO `item`(`id`, `carton_price`, `description`, `unit_per_carton`, `unit_price`) VALUES (1, 175, 'Penguin-ears', 20, 8.75);
INSERT INTO `item`(`id`, `carton_price`, `description`, `unit_per_carton`, `unit_price`) VALUES (2, 825, 'Horseshoe', 5, 165);

