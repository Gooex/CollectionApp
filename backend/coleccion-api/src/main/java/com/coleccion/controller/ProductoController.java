package com.coleccion.controller;

import com.coleccion.dto.ProductoResponse;
import com.coleccion.dto.ImagenProductoResponse;
import com.coleccion.repository.ImagenProductoRepository;
import com.coleccion.entity.EstadoColeccion;
import com.coleccion.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService service;
    private final ImagenProductoRepository imagenRepository;

    public ProductoController(ProductoService service, ImagenProductoRepository imagenRepository) {
        this.service = service;
        this.imagenRepository = imagenRepository;
    }

    @GetMapping
    public List<ProductoResponse> obtenerTodos(@RequestParam(required = false) Long categoriaId) {
        var productos = categoriaId != null ? service.obtenerPorCategoria(categoriaId) : service.obtenerTodos();
        return productos.stream().map(ProductoResponse::desde).toList();
    }

    @GetMapping("/{id}")
    public ProductoResponse obtenerPorId(@PathVariable Long id) {
        return ProductoResponse.desde(service.obtenerPorId(id));
    }

    @GetMapping("/{id}/imagenes")
    public List<ImagenProductoResponse> obtenerImagenes(@PathVariable Long id) {
        service.obtenerPorId(id); // Valida que el producto exista.
        return imagenRepository.findByProductoIdOrderByOrdenAscIdAsc(id)
                .stream().map(ImagenProductoResponse::desde).toList();
    }

    @PutMapping("/{id}/estado")
    public ProductoResponse cambiarEstado(@PathVariable Long id, @RequestParam EstadoColeccion estado) {
        return ProductoResponse.desde(service.cambiarEstado(id, estado));
    }
}
