-- Ejecutar una sola vez sobre la base coleccion_db antes de reiniciar Spring Boot.
-- Agrega los campos que utiliza la ficha detallada del producto.

ALTER TABLE producto
    ADD COLUMN IF NOT EXISTS numero_modelo VARCHAR(100),
    ADD COLUMN IF NOT EXISTS precio_compra NUMERIC(12,2),
    ADD COLUMN IF NOT EXISTS fecha_compra DATE,
    ADD COLUMN IF NOT EXISTS lugar_compra VARCHAR(200),
    ADD COLUMN IF NOT EXISTS notas_compra VARCHAR(1000);

-- Ejemplos opcionales de cómo registrar fotografías adicionales para un producto.
-- Cambia 1 por el ID real del producto y usa rutas que existan dentro de Angular.
-- INSERT INTO imagen_producto (url, tipo, orden, producto_id)
-- VALUES ('/assets/images/products/nissan-skyline-lateral.jpg', 'GALERIA', 1, 1);
-- INSERT INTO imagen_producto (url, tipo, orden, producto_id)
-- VALUES ('/assets/images/products/nissan-skyline-caja-frente.jpg', 'CAJA', 1, 1);
