package com.coleccion.controller;

import com.coleccion.entity.EstadoColeccion;
import com.coleccion.entity.Producto;
import com.coleccion.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> obtenerTodos(
            @RequestParam(required = false) Long categoriaId) {
        if (categoriaId != null) {
            return service.obtenerPorCategoria(categoriaId);
        }
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}/estado")
    public Producto cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoColeccion estado) {
        return service.cambiarEstado(id, estado);
    }
}
