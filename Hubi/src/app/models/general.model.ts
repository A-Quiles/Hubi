export interface Categoria {
  id: number;
  nombre: string;
}

export interface Publicacion {
  id?: number;
  idUsuario: number;
  mensaje: string;
  fecha: string;
  categoria?: Categoria;
  nombre: string;
  imagen?: string;
  hashtags?: string[];
}

