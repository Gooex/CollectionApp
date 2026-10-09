package com.coleccion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    private String marca;
    private String modelo;
    private Integer anio;
    private String escala;

    @Column(length = 1000)
    private String descripcion;

    @Column(name = "imagen_principal")
    private String imagenPrincipal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoColeccion estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    @JsonIgnore
    private Categoria categoria;

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public Integer getAnio() { return anio; }
    public String getEscala() { return escala; }
    public String getDescripcion() { return descripcion; }
    public String getImagenPrincipal() { return imagenPrincipal; }
    public EstadoColeccion getEstado() { return estado; }
    public Categoria getCategoria() { return categoria; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public void setEscala(String escala) { this.escala = escala; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setImagenPrincipal(String imagenPrincipal) { this.imagenPrincipal = imagenPrincipal; }
    public void setEstado(EstadoColeccion estado) { this.estado = estado; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
