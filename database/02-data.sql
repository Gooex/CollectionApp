INSERT INTO coleccion (nombre, descripcion) VALUES
('LEGO', 'Colección de sets LEGO'),
('Pokémon Select', 'Figuras de Pokémon Select'),
('Anime', 'Figuras y coleccionables de anime'),
('Mattel Brick Shop', 'Modelos de autos de Mattel Brick Shop');

INSERT INTO categoria (nombre, descripcion, coleccion_id)
SELECT 'Star Wars', 'Sets de Star Wars', id FROM coleccion WHERE nombre = 'LEGO';

INSERT INTO categoria (nombre, descripcion, coleccion_id)
SELECT 'Autos 16 pulgadas', 'Modelos de aproximadamente 16 pulgadas', id FROM coleccion WHERE nombre = 'Mattel Brick Shop';

INSERT INTO categoria (nombre, descripcion, coleccion_id)
SELECT 'Figuras', 'Figuras Pokémon Select', id FROM coleccion WHERE nombre = 'Pokémon Select';

INSERT INTO categoria (nombre, descripcion, coleccion_id)
SELECT 'Figuras', 'Figuras de anime', id FROM coleccion WHERE nombre = 'Anime';

INSERT INTO producto
(nombre, marca, modelo, anio, escala, descripcion, imagen_principal, estado, categoria_id)
SELECT
'Nissan Skyline', 'Mattel Brick Shop', 'Nissan Skyline', 2026, '16 pulgadas',
'Modelo de ejemplo para probar el catálogo.',
'https://placehold.co/600x400?text=Nissan+Skyline',
'COMPRADO', id
FROM categoria WHERE nombre = 'Autos 16 pulgadas'
LIMIT 1;

INSERT INTO producto
(nombre, marca, modelo, anio, escala, descripcion, imagen_principal, estado, categoria_id)
SELECT
'Toyota Supra', 'Mattel Brick Shop', 'Toyota Supra', 2026, '16 pulgadas',
'Modelo de ejemplo marcado como por comprar.',
'https://placehold.co/600x400?text=Toyota+Supra',
'POR_COMPRAR', id
FROM categoria WHERE nombre = 'Autos 16 pulgadas'
LIMIT 1;

INSERT INTO producto
(nombre, marca, modelo, anio, escala, descripcion, imagen_principal, estado, categoria_id)
SELECT
'AT-AT', 'LEGO', '75313', 2021, 'Ultimate Collector Series',
'Modelo de ejemplo de LEGO.',
'https://placehold.co/600x400?text=LEGO+AT-AT',
'PENDIENTE', id
FROM categoria WHERE nombre = 'Star Wars'
LIMIT 1;
