package com.coleccion.controller;

import com.coleccion.entity.Coleccion;
import com.coleccion.service.ColeccionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/colecciones")
public class ColeccionController {

    private final ColeccionService service;

    public ColeccionController(ColeccionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Coleccion> obtenerTodas() {
        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Coleccion obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }
}
