package com.coleccion.controller;

import com.coleccion.entity.Categoria;
import com.coleccion.service.CategoriaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Categoria> obtenerTodas(
            @RequestParam(required = false) Long coleccionId) {
        if (coleccionId != null) {
            return service.obtenerPorColeccion(coleccionId);
        }
        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Categoria obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }
}
