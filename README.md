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
# Paso 2: ficha detallada del producto

Este paquete contiene solo los archivos nuevos/modificados para agregar la vista individual del producto, galería, fotografías de caja, zoom, número de modelo y datos de compra.

## 1. Aplicar los archivos

Copia el contenido del ZIP dentro de la carpeta raíz de tu proyecto `coleccion-app`, conservando las rutas y permitiendo combinar los archivos existentes. No reemplaces toda tu carpeta de proyecto.

## 2. Actualizar PostgreSQL antes de arrancar Spring Boot

En DBeaver, selecciona la base `coleccion_db`, abre un editor SQL y ejecuta una sola vez:

`database/migrations/04-ficha-detallada-producto.sql`

La aplicación tiene `spring.jpa.hibernate.ddl-auto=validate`; por eso es necesario agregar las columnas antes de iniciar Spring Boot. Ejecuta el script como el propietario de la tabla o un usuario con permisos ALTER.

## 3. Reiniciar backend y frontend

- Reinicia Spring Boot.
- Angular puede seguir levantado; si no detecta la ruta nueva, detén `ng serve` y vuelve a iniciarlo.
- Abre `/catalogo` y pulsa **Ver ficha completa** en una tarjeta. La ruta será `/productos/ID`.

## 4. Agregar imágenes adicionales

Guarda los archivos dentro de `frontend/coleccion-web/src/assets/images/products/`. Registra cada ruta en la tabla `imagen_producto`. Usa `GALERIA` para fotos del producto y `CAJA` para fotos del empaque. Ejemplo (cambia el ID por el ID real):

```sql
INSERT INTO imagen_producto (url, tipo, orden, producto_id)
VALUES ('/assets/images/products/nissan-skyline-lateral.jpg', 'GALERIA', 1, 1);

INSERT INTO imagen_producto (url, tipo, orden, producto_id)
VALUES ('/assets/images/products/nissan-skyline-caja-frente.jpg', 'CAJA', 1, 1);
```

La imagen principal sigue utilizando `producto.imagen_principal`. Las imágenes adicionales se guardan en `imagen_producto`.

## 5. Datos de compra

Los campos nuevos son `numero_modelo`, `precio_compra`, `fecha_compra`, `lugar_compra` y `notas_compra`. Se muestran en la ficha y pueden actualizarse directamente en PostgreSQL, por ejemplo:

```sql
UPDATE producto
SET numero_modelo = 'JBY82',
    precio_compra = 349.00,
    fecha_compra = DATE '2026-10-10',
    lugar_compra = 'Tienda / vendedor',
    notas_compra = 'Caja en buen estado'
WHERE id = 1;
```

Cambia los valores de ejemplo y confirma el ID antes de ejecutar.

## Nota

Este paso agrega la consulta y visualización de las fotografías/datos. La edición de los datos desde un formulario dentro de Angular puede añadirse como una siguiente mejora.

#### Parche: modo oscuro para Mi Colección

Este parche añade un botón en la barra superior para alternar entre modo oscuro y claro.
La preferencia queda guardada en el navegador (`localStorage`) y se conserva al recargar.

## Instalación
1. Haz una copia de seguridad de estos archivos en tu proyecto.
2. Extrae el ZIP en la raíz de tu proyecto y combina las carpetas:
   - `frontend/coleccion-web/src/app/app.component.html`
   - `frontend/coleccion-web/src/app/app.component.ts`
   - `frontend/coleccion-web/src/styles.css`
3. Si ya hiciste cambios propios en `app.component.html`, `app.component.ts` o `styles.css`, no los sobrescribas sin revisar: integra el botón, los métodos `alternarTema`/`aplicarTema` y los estilos al final de tu archivo CSS.
4. No necesita cambios en Spring Boot ni en PostgreSQL.
5. Angular debería recargar automáticamente. Si no, detén y vuelve a iniciar `ng serve`.

El CSS se basa en la hoja de estilos de la versión con catálogo y ficha detallada.
