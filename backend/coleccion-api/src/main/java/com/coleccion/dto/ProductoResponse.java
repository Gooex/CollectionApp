package com.coleccion.dto;

import com.coleccion.entity.EstadoColeccion;
import com.coleccion.entity.Producto;

public record ProductoResponse(
        Long id, String nombre, String marca, String modelo, Integer anio,
        String escala, String descripcion, String imagenPrincipal,
        EstadoColeccion estado, Long categoriaId, String categoriaNombre,
        Long coleccionId, String coleccionNombre) {

    public static ProductoResponse desde(Producto p) {
        var categoria = p.getCategoria();
        var coleccion = categoria == null ? null : categoria.getColeccion();
        return new ProductoResponse(
                p.getId(), p.getNombre(), p.getMarca(), p.getModelo(), p.getAnio(),
                p.getEscala(), p.getDescripcion(), p.getImagenPrincipal(), p.getEstado(),
                categoria == null ? null : categoria.getId(),
                categoria == null ? null : categoria.getNombre(),
                coleccion == null ? null : coleccion.getId(),
                coleccion == null ? null : coleccion.getNombre());
    }
}
