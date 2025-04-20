import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Publicacion } from '../models/general.model';

@Injectable({
  providedIn: 'root'
})
export class Publicaction {
  private urlPublicaciones = 'http://localhost:8090/publicaciones';

  constructor(private httpClient: HttpClient) { }

  /** 
   * Ahora recibe opcionalmente categoría y hashtag.
   * El hashtag debe venir sin la ‘#’ (en tu componente lo limpias).
   */
  getPublicaciones(
    categoriaId?: number | null,
    hashtags?: string[]       // ahora array
  ): Observable<Publicacion[]> {
    let params = new HttpParams();
    if (categoriaId != null) {
      params = params.set('categoriaId', categoriaId.toString());
    }
    // por cada tag hacemos un append('hashtag', tag)
    if (hashtags && hashtags.length) {
      hashtags.forEach(tag => {
        params = params.append('hashtag', tag);
      });
    }
    return this.httpClient.get<Publicacion[]>(this.urlPublicaciones, { params });
  }

  createPublicacion(
    publicacion: Publicacion,
    imagen: File | null
  ): Observable<Publicacion> {
    const formData = new FormData();
    formData.append('mensaje', publicacion.mensaje);
    formData.append('nombre', publicacion.nombre);
    formData.append('idUsuario', publicacion.idUsuario.toString());
    formData.append('fecha', publicacion.fecha);
    if (publicacion.categoria) {
      formData.append('categoria', publicacion.categoria.id.toString());
    }
    if (imagen) {
      formData.append('imagen', imagen, imagen.name);
    }
    return this.httpClient.post<Publicacion>(this.urlPublicaciones, formData);
  }
}
