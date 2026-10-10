import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categoria } from '../models/categoria.model';

@Injectable({ providedIn: 'root' })
export class CategoriaService {
  private http = inject(HttpClient);
  private api = 'http://localhost:8080/api';

  obtenerCategorias(coleccionId?: number): Observable<Categoria[]> {
    const params = coleccionId ? `?coleccionId=${coleccionId}` : '';
    return this.http.get<Categoria[]>(`${this.api}/categorias${params}`);
  }
}
