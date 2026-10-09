import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto, EstadoColeccion } from '../models/producto.model';

@Injectable({ providedIn: 'root' })
export class ProductoService {
  private http = inject(HttpClient);
  private api = 'http://localhost:8080/api';

  obtenerProductos(categoriaId?: number): Observable<Producto[]> {
    const url = categoriaId
      ? `${this.api}/productos?categoriaId=${categoriaId}`
      : `${this.api}/productos`;

    return this.http.get<Producto[]>(url);
  }

  cambiarEstado(id: number, estado: EstadoColeccion): Observable<Producto> {
    return this.http.put<Producto>(
      `${this.api}/productos/${id}/estado?estado=${estado}`,
      {}
    );
  }
}
