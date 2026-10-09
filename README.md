# Coleccion App

Proyecto inicial para gestionar colecciones (LEGO, Pokémon Select, Anime, Mattel Brick Shop, etc.).

## Stack

- Backend: Java 17 + Spring Boot 3.5.6 + Spring Data JPA
- Base de datos: PostgreSQL 16+
- Frontend: Angular 20 + TypeScript
- API REST: JSON

## 1. Crear la base de datos

```sql
CREATE DATABASE coleccion_db;
CREATE USER coleccion_user WITH PASSWORD 'coleccion_pass';
GRANT ALL PRIVILEGES ON DATABASE coleccion_db TO coleccion_user;
```

Después ejecuta `database/01-schema.sql` y `database/02-data.sql`.

## 2. Backend

Requisitos: Java 17+ y Maven 3.9+.

```bash
cd backend/coleccion-api
mvn spring-boot:run
```

API:
`http://localhost:8080/api`

## 3. Frontend

Requisitos: Node.js 20+ y npm.

```bash
cd frontend/coleccion-web
npm install
npm start
```

Abrir:
`http://localhost:4200`

El frontend usa `http://localhost:8080/api`.

## Endpoints iniciales

- GET `/api/colecciones`
- GET `/api/colecciones/{id}`
- GET `/api/categorias`
- GET `/api/categorias/{id}`
- GET `/api/productos`
- GET `/api/productos/{id}`
- GET `/api/productos?categoriaId=1`
- PUT `/api/productos/{id}/estado?estado=COMPRADO`

Estados:
- COMPRADO
- POR_COMPRAR
- NO_INTERESA
- PENDIENTE

## Siguiente etapa

1. Autenticación de usuario.
2. Estado por usuario (no global).
3. Galería de imágenes.
4. Fotos 360°.
5. Zoom.
6. Carga de imágenes desde administración.
7. Dashboard con porcentajes y estadísticas.
8. Convertir el frontend en aplicación Android mediante Capacitor.
