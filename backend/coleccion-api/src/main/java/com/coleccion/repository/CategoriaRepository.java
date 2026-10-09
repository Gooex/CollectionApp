package com.coleccion.repository;

import com.coleccion.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByColeccionId(Long coleccionId);
}
