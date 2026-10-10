package com.coleccion.controller;

import com.coleccion.dto.ProductoResponse;
import com.coleccion.entity.EstadoColeccion;
import com.coleccion.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService service;
    public ProductoController(ProductoService service) { this.service = service; }

    @GetMapping
    public List<ProductoResponse> obtenerTodos(@RequestParam(required = false) Long categoriaId) {
        var productos = categoriaId != null ? service.obtenerPorCategoria(categoriaId) : service.obtenerTodos();
        return productos.stream().map(ProductoResponse::desde).toList();
    }

    @GetMapping("/{id}")
    public ProductoResponse obtenerPorId(@PathVariable Long id) {
        return ProductoResponse.desde(service.obtenerPorId(id));
    }

    @PutMapping("/{id}/estado")
    public ProductoResponse cambiarEstado(@PathVariable Long id, @RequestParam EstadoColeccion estado) {
        return ProductoResponse.desde(service.cambiarEstado(id, estado));
    }
}
