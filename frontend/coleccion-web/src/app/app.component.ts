import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  modoOscuro = false;

  ngOnInit(): void {
    // Recupera la preferencia guardada en este navegador.
    this.modoOscuro = localStorage.getItem('tema') === 'oscuro';
    this.aplicarTema();
  }

  alternarTema(): void {
    this.modoOscuro = !this.modoOscuro;
    localStorage.setItem('tema', this.modoOscuro ? 'oscuro' : 'claro');
    this.aplicarTema();
  }

  private aplicarTema(): void {
    document.documentElement.classList.toggle('dark-mode', this.modoOscuro);
  }
}
