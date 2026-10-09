CREATE TABLE IF NOT EXISTS coleccion (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    imagen VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS categoria (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    coleccion_id BIGINT NOT NULL REFERENCES coleccion(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS producto (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    marca VARCHAR(100),
    modelo VARCHAR(150),
    anio INTEGER,
    escala VARCHAR(80),
    descripcion VARCHAR(1000),
    imagen_principal VARCHAR(500),
    estado VARCHAR(30) NOT NULL DEFAULT 'PENDIENTE',
    categoria_id BIGINT NOT NULL REFERENCES categoria(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS imagen_producto (
    id BIGSERIAL PRIMARY KEY,
    url VARCHAR(500) NOT NULL,
    tipo VARCHAR(30) NOT NULL DEFAULT 'GALERIA',
    orden INTEGER DEFAULT 0,
    producto_id BIGINT NOT NULL REFERENCES producto(id) ON DELETE CASCADE
);
