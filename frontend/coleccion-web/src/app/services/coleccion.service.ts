import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Coleccion } from '../models/coleccion.model';

@Injectable({ providedIn: 'root' })
export class ColeccionService {
  private http = inject(HttpClient);
  private api = 'http://localhost:8080/api';

  obtenerColecciones(): Observable<Coleccion[]> {
    return this.http.get<Coleccion[]>(`${this.api}/colecciones`);
  }
}
