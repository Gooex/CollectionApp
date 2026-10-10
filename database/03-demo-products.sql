-- Datos de demostración para poblar el catálogo sin duplicar estos modelos.
-- Ejecuta este archivo en la misma base de datos configurada en application.properties.

INSERT INTO producto
(nombre, marca, modelo, anio, escala, descripcion, imagen_principal, estado, categoria_id)
SELECT 'Nissan Skyline', 'Mattel Brick Shop', 'Nissan Skyline GT-R', 2026, '16 pulgadas',
       'Modelo de demostración. Sustituye la imagen por una fotografía de tu pieza.',
       '/assets/images/products/nissan-skyline.svg', 'COMPRADO', c.id
FROM categoria c
JOIN coleccion co ON co.id = c.coleccion_id
WHERE c.nombre = 'Autos 16 pulgadas' AND co.nombre = 'Mattel Brick Shop'
  AND NOT EXISTS (SELECT 1 FROM producto p WHERE p.nombre = 'Nissan Skyline' AND p.categoria_id = c.id);

INSERT INTO producto
(nombre, marca, modelo, anio, escala, descripcion, imagen_principal, estado, categoria_id)
SELECT 'Toyota Supra', 'Mattel Brick Shop', 'Toyota Supra', 2026, '16 pulgadas',
       'Modelo de demostración marcado como por comprar.',
       '/assets/images/products/toyota-supra.svg', 'POR_COMPRAR', c.id
FROM categoria c
JOIN coleccion co ON co.id = c.coleccion_id
WHERE c.nombre = 'Autos 16 pulgadas' AND co.nombre = 'Mattel Brick Shop'
  AND NOT EXISTS (SELECT 1 FROM producto p WHERE p.nombre = 'Toyota Supra' AND p.categoria_id = c.id);

INSERT INTO producto
(nombre, marca, modelo, anio, escala, descripcion, imagen_principal, estado, categoria_id)
SELECT 'AT-AT', 'LEGO', '75313', 2021, 'Ultimate Collector Series',
       'Modelo de demostración de la colección LEGO Star Wars.',
       '/assets/images/products/lego-atat.svg', 'PENDIENTE', c.id
FROM categoria c
JOIN coleccion co ON co.id = c.coleccion_id
WHERE c.nombre = 'Star Wars' AND co.nombre = 'LEGO'
  AND NOT EXISTS (SELECT 1 FROM producto p WHERE p.nombre = 'AT-AT' AND p.categoria_id = c.id);
