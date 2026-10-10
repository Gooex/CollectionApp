import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CatalogoComponent } from './components/catalogo/catalogo.component';
import { DetalleProductoComponent } from './components/detalle-producto/detalle-producto.component';

export const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'catalogo', component: CatalogoComponent },
  { path: 'productos/:id', component: DetalleProductoComponent },
  { path: '**', redirectTo: '' }
];
