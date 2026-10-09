package com.coleccion.service;

import com.coleccion.entity.Categoria;
import com.coleccion.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> obtenerTodas() {
        return repository.findAll();
    }

    public List<Categoria> obtenerPorColeccion(Long coleccionId) {
        return repository.findByColeccionId(coleccionId);
    }

    public Categoria obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }
}
