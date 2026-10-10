package com.coleccion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Column(name = "numero_modelo", length = 100)
    private String numeroModelo;

    @Column(name = "precio_compra", precision = 12, scale = 2)
    private BigDecimal precioCompra;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @Column(name = "lugar_compra", length = 200)
    private String lugarCompra;

    @Column(name = "notas_compra", length = 1000)
    private String notasCompra;

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
    public String getNumeroModelo() { return numeroModelo; }
    public BigDecimal getPrecioCompra() { return precioCompra; }
    public LocalDate getFechaCompra() { return fechaCompra; }
    public String getLugarCompra() { return lugarCompra; }
    public String getNotasCompra() { return notasCompra; }
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
    public void setNumeroModelo(String numeroModelo) { this.numeroModelo = numeroModelo; }
    public void setPrecioCompra(BigDecimal precioCompra) { this.precioCompra = precioCompra; }
    public void setFechaCompra(LocalDate fechaCompra) { this.fechaCompra = fechaCompra; }
    public void setLugarCompra(String lugarCompra) { this.lugarCompra = lugarCompra; }
    public void setNotasCompra(String notasCompra) { this.notasCompra = notasCompra; }
    public void setEstado(EstadoColeccion estado) { this.estado = estado; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
