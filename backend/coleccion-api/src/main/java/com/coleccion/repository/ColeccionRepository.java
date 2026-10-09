package com.coleccion.repository;

import com.coleccion.entity.Coleccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColeccionRepository extends JpaRepository<Coleccion, Long> {
}
