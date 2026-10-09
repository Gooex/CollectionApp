export type EstadoColeccion =
  | 'COMPRADO'
  | 'POR_COMPRAR'
  | 'NO_INTERESA'
  | 'PENDIENTE';

export interface Producto {
  id: number;
  nombre: string;
  marca?: string;
  modelo?: string;
  anio?: number;
  escala?: string;
  descripcion?: string;
  imagenPrincipal?: string;
  estado: EstadoColeccion;
}
