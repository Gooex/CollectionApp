package com.coleccion.dto;

import com.coleccion.entity.ImagenProducto;

public record ImagenProductoResponse(Long id, String url, String tipo, Integer orden) {
    public static ImagenProductoResponse desde(ImagenProducto imagen) {
        return new ImagenProductoResponse(imagen.getId(), imagen.getUrl(), imagen.getTipo(), imagen.getOrden());
    }
}
