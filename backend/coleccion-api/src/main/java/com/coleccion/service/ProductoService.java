package com.coleccion.service;

import com.coleccion.entity.EstadoColeccion;
import com.coleccion.entity.Producto;
import com.coleccion.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }

    public List<Producto> obtenerPorCategoria(Long categoriaId) {
        return repository.findByCategoriaId(categoriaId);
    }

    public Producto obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto cambiarEstado(Long id, EstadoColeccion estado) {
        Producto producto = obtenerPorId(id);
        producto.setEstado(estado);
        return repository.save(producto);
    }
}
