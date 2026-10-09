import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CatalogoComponent } from './components/catalogo/catalogo.component';

export const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'catalogo', component: CatalogoComponent },
  { path: '**', redirectTo: '' }
];
