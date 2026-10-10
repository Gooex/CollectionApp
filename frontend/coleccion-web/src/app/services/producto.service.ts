import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto, EstadoColeccion, ImagenProducto } from '../models/producto.model';

@Injectable({ providedIn: 'root' })
export class ProductoService {
  private http = inject(HttpClient);
  private api = 'http://localhost:8080/api';

  obtenerProductos(categoriaId?: number): Observable<Producto[]> {
    const params = categoriaId ? `?categoriaId=${categoriaId}` : '';
    return this.http.get<Producto[]>(`${this.api}/productos${params}`);
  }

  obtenerProducto(id: number): Observable<Producto> {
    return this.http.get<Producto>(`${this.api}/productos/${id}`);
  }

  obtenerImagenes(id: number): Observable<ImagenProducto[]> {
    return this.http.get<ImagenProducto[]>(`${this.api}/productos/${id}/imagenes`);
  }

  cambiarEstado(id: number, estado: EstadoColeccion): Observable<Producto> {
    return this.http.put<Producto>(
      `${this.api}/productos/${id}/estado?estado=${estado}`, {}
    );
  }
}
