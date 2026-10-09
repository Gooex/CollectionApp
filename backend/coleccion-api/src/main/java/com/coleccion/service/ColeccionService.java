package com.coleccion.service;

import com.coleccion.entity.Coleccion;
import com.coleccion.repository.ColeccionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ColeccionService {

    private final ColeccionRepository repository;

    public ColeccionService(ColeccionRepository repository) {
        this.repository = repository;
    }

    public List<Coleccion> obtenerTodas() {
        return repository.findAll();
    }

    public Coleccion obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colección no encontrada"));
    }
}
