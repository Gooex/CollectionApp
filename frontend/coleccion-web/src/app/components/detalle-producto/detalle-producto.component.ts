import { CommonModule } from '@angular/common';
import { Component, HostListener, OnInit, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { forkJoin } from 'rxjs';
import { Producto, ImagenProducto } from '../../models/producto.model';
import { ProductoService } from '../../services/producto.service';

@Component({
  selector: 'app-detalle-producto',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './detalle-producto.component.html'
})
export class DetalleProductoComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private productoService = inject(ProductoService);

  producto: Producto | null = null;
  imagenes: ImagenProducto[] = [];
  imagenesGaleria: ImagenProducto[] = [];
  imagenesCaja: ImagenProducto[] = [];
  imagenSeleccionada = '';
  imagenZoom = '';
  cargando = true;
  error = '';

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!Number.isInteger(id) || id <= 0) {
      this.error = 'El identificador del producto no es válido.';
      this.cargando = false;
      return;
    }

    forkJoin({
      producto: this.productoService.obtenerProducto(id),
      imagenes: this.productoService.obtenerImagenes(id)
    }).subscribe({
      next: ({ producto, imagenes }) => {
        this.producto = producto;
        this.imagenes = imagenes ?? [];
        this.imagenesGaleria = this.imagenes.filter(i => i.tipo?.toUpperCase() !== 'CAJA');
        this.imagenesCaja = this.imagenes.filter(i => i.tipo?.toUpperCase() === 'CAJA');
        this.imagenSeleccionada = this.imagenesGaleria[0]?.url || producto.imagenPrincipal || '/assets/images/products/producto-generico.svg';
        this.cargando = false;
      },
      error: err => {
        console.error('Error cargando ficha de producto', err);
        this.error = 'No fue posible cargar la ficha. Comprueba que Spring Boot esté funcionando y que el producto exista.';
        this.cargando = false;
      }
    });
  }

  seleccionarImagen(url: string): void { this.imagenSeleccionada = url; }
  abrirZoom(url?: string): void { this.imagenZoom = url || this.imagenSeleccionada; }
  cerrarZoom(): void { this.imagenZoom = ''; }

  @HostListener('document:keydown.escape')
  alPresionarEscape(): void { this.cerrarZoom(); }

  imagenFallback(event: Event): void {
    const img = event.target as HTMLImageElement;
    img.onerror = null;
    img.src = '/assets/images/products/producto-generico.svg';
  }
}
