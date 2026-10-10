import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { forkJoin } from 'rxjs';
import { ProductoService } from '../../services/producto.service';
import { ColeccionService } from '../../services/coleccion.service';
import { CategoriaService } from '../../services/categoria.service';
import { Producto, EstadoColeccion, ESTADOS } from '../../models/producto.model';
import { Coleccion } from '../../models/coleccion.model';
import { Categoria } from '../../models/categoria.model';

@Component({
  selector: 'app-catalogo',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './catalogo.component.html'
})
export class CatalogoComponent implements OnInit {
  private productoService = inject(ProductoService);
  private coleccionService = inject(ColeccionService);
  private categoriaService = inject(CategoriaService);

  productos: Producto[] = [];
  productosFiltrados: Producto[] = [];
  colecciones: Coleccion[] = [];
  categorias: Categoria[] = [];
  estados = ESTADOS;
  busqueda = '';
  coleccionSeleccionada = '';
  categoriaSeleccionada = '';
  estadoSeleccionado = '';
  cargando = true;
  errorCarga = '';
  actualizandoId: number | null = null;

  ngOnInit(): void { this.cargarDatos(); }

  cargarDatos(): void {
    this.cargando = true;
    this.errorCarga = '';
    forkJoin({
      productos: this.productoService.obtenerProductos(),
      colecciones: this.coleccionService.obtenerColecciones(),
      categorias: this.categoriaService.obtenerCategorias()
    }).subscribe({
      next: datos => {
        this.productos = datos.productos ?? [];
        this.colecciones = datos.colecciones ?? [];
        this.categorias = datos.categorias ?? [];
        this.filtrar();
        this.cargando = false;
      },
      error: err => {
        console.error('Error cargando catálogo', err);
        this.errorCarga = 'No fue posible consultar el catálogo. Verifica que Spring Boot esté levantado y que existan datos en PostgreSQL.';
        this.cargando = false;
      }
    });
  }

  get categoriasDisponibles(): Categoria[] {
    if (!this.coleccionSeleccionada) return this.categorias;
    // La API de categorías devuelve la lista filtrada por colección.
    return this.categorias.filter(c => !('coleccionId' in c) || true);
  }

  alCambiarColeccion(): void {
    this.categoriaSeleccionada = '';
    const id = this.coleccionSeleccionada ? Number(this.coleccionSeleccionada) : undefined;
    this.categoriaService.obtenerCategorias(id).subscribe({
      next: categorias => { this.categorias = categorias; this.filtrar(); },
      error: err => console.error('Error cargando categorías', err)
    });
    this.filtrar();
  }

  filtrar(): void {
    const texto = this.busqueda.trim().toLocaleLowerCase();
    this.productosFiltrados = this.productos.filter(p => {
      const coincideTexto = !texto || [p.nombre, p.marca, p.modelo, p.categoriaNombre, p.coleccionNombre]
        .some(v => (v ?? '').toLocaleLowerCase().includes(texto));
      const coincideColeccion = !this.coleccionSeleccionada || p.coleccionId === Number(this.coleccionSeleccionada);
      const coincideCategoria = !this.categoriaSeleccionada || p.categoriaId === Number(this.categoriaSeleccionada);
      const coincideEstado = !this.estadoSeleccionado || p.estado === this.estadoSeleccionado;
      return coincideTexto && coincideColeccion && coincideCategoria && coincideEstado;
    });
  }

  cambiarEstado(producto: Producto, estado: EstadoColeccion): void {
    if (this.actualizandoId !== null || producto.estado === estado) return;
    this.actualizandoId = producto.id;
    this.productoService.cambiarEstado(producto.id, estado).subscribe({
      next: actualizado => {
        producto.estado = actualizado.estado;
        this.actualizandoId = null;
        this.filtrar();
      },
      error: err => {
        console.error('Error cambiando estado', err);
        this.errorCarga = 'No se pudo actualizar el estado. Inténtalo de nuevo.';
        this.actualizandoId = null;
      }
    });
  }

  claseEstado(estado: EstadoColeccion): string { return estado.toLowerCase().replace('_', '-'); }

  etiquetaEstado(estado: EstadoColeccion): string {
    return this.estados.find(e => e.valor === estado)?.etiqueta ?? estado;
  }

  imagenFallback(event: Event): void {
    const img = event.target as HTMLImageElement;
    img.onerror = null;
    img.src = '/assets/images/products/producto-generico.svg';
  }
}
