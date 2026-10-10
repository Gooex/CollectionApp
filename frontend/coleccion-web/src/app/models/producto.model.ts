export type EstadoColeccion = 'COMPRADO' | 'POR_COMPRAR' | 'NO_INTERESA' | 'PENDIENTE';

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
  categoriaId?: number;
  categoriaNombre?: string;
  coleccionId?: number;
  coleccionNombre?: string;
}

export const ESTADOS: { valor: EstadoColeccion; etiqueta: string }[] = [
  { valor: 'COMPRADO', etiqueta: 'Comprado' },
  { valor: 'POR_COMPRAR', etiqueta: 'Por comprar' },
  { valor: 'PENDIENTE', etiqueta: 'Pendiente' },
  { valor: 'NO_INTERESA', etiqueta: 'No interesa' }
];
