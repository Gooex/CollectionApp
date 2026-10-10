package com.coleccion.repository;

import com.coleccion.entity.ImagenProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Long> {
    List<ImagenProducto> findByProductoIdOrderByOrdenAscIdAsc(Long productoId);
}
