import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ColeccionService } from '../../services/coleccion.service';
import { Coleccion } from '../../models/coleccion.model';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  private service = inject(ColeccionService);

  colecciones: Coleccion[] = [];

  ngOnInit(): void {
    this.service.obtenerColecciones().subscribe({
      next: data => this.colecciones = data,
      error: error => console.error('Error cargando colecciones', error)
    });
  }
}
