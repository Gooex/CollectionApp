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

# Cambios de catálogo — Colección App

Esto es un **parche** para copiar sobre el proyecto inicial, conservando el resto de los archivos.

## Archivos incluidos

### Frontend Angular
- `frontend/coleccion-web/angular.json` — habilita `src/assets` para servir imágenes locales.
- `frontend/coleccion-web/src/styles.css` — estilos de tarjetas, filtros, estados y diseño adaptable.
- `frontend/coleccion-web/src/app/components/catalogo/catalogo.component.ts` — carga de productos y filtros.
- `frontend/coleccion-web/src/app/components/catalogo/catalogo.component.html` — interfaz del catálogo.
- `frontend/coleccion-web/src/app/models/producto.model.ts` — modelo de producto y estados.
- `frontend/coleccion-web/src/app/models/categoria.model.ts` — modelo de categoría nuevo.
- `frontend/coleccion-web/src/app/services/categoria.service.ts` — servicio de categorías nuevo.
- `frontend/coleccion-web/src/app/services/producto.service.ts` — conserva los endpoints actuales.
- `frontend/coleccion-web/src/assets/images/products/*.svg` — imágenes locales de demostración.

### Backend Spring Boot
- `backend/coleccion-api/src/main/java/com/coleccion/dto/ProductoResponse.java` — respuesta con datos de categoría y colección.
- `backend/coleccion-api/src/main/java/com/coleccion/controller/ProductoController.java` — responde con el DTO para el catálogo.

### PostgreSQL
- `database/03-demo-products.sql` — inserta tres productos de prueba sin duplicarlos si ya existen.

## Cómo aplicar

1. Haz una copia de seguridad de tu proyecto actual o guarda los cambios en Git.
2. Descomprime este paquete y copia la carpeta `frontend`, `backend` y `database` sobre las carpetas equivalentes de tu proyecto, aceptando reemplazar los archivos indicados.
3. En PostgreSQL, ejecuta `database/03-demo-products.sql` sobre la misma base de datos configurada en `application.properties`. El script requiere que ya existan las colecciones `LEGO` y `Mattel Brick Shop` y las categorías `Star Wars` y `Autos 16 pulgadas` creadas por el script inicial.
4. Reinicia Spring Boot para compilar el nuevo DTO y controlador.
5. Reinicia Angular (`npm start`) o recarga la aplicación.

## Imágenes locales

Las rutas de imágenes se guardan en PostgreSQL como `/assets/images/products/nissan-skyline.svg`. Angular las sirve desde `src/assets`, por lo que no se guardan binarios en la base de datos. Sustituye los SVG de ejemplo por fotos propias y actualiza `imagen_principal` con una ruta relativa equivalente, por ejemplo `/assets/images/products/mi-modelo.jpg`.

## Comportamiento de los filtros

La búsqueda, colección, categoría y estado se pueden combinar. El estado cambia con los botones y se persiste mediante el endpoint `PUT /api/productos/{id}/estado?estado=...`. Las tarjetas `NO_INTERESA` muestran solo la imagen, el estado y el nombre; no presentan botones de acción.
