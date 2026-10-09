import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductoService } from '../../services/producto.service';
import { Producto, EstadoColeccion } from '../../models/producto.model';

@Component({
  selector: 'app-catalogo',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './catalogo.component.html'
})
export class CatalogoComponent implements OnInit {
  private service = inject(ProductoService);

  productos: Producto[] = [];

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.service.obtenerProductos().subscribe({
      next: data => this.productos = data,
      error: error => console.error('Error cargando productos', error)
    });
  }

  cambiarEstado(producto: Producto, estado: EstadoColeccion): void {
    this.service.cambiarEstado(producto.id, estado).subscribe({
      next: actualizado => producto.estado = actualizado.estado,
      error: error => console.error('Error cambiando estado', error)
    });
  }

  claseEstado(estado: EstadoColeccion): string {
    return estado.toLowerCase().replace('_', '-');
  }
}
