package com.coleccion.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "imagen_producto")
public class ImagenProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(nullable = false, length = 30)
    private String tipo = "GALERIA";

    private Integer orden = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public Long getId() { return id; }
    public String getUrl() { return url; }
    public String getTipo() { return tipo; }
    public Integer getOrden() { return orden; }
    public Producto getProducto() { return producto; }

    public void setId(Long id) { this.id = id; }
    public void setUrl(String url) { this.url = url; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setOrden(Integer orden) { this.orden = orden; }
    public void setProducto(Producto producto) { this.producto = producto; }
}
