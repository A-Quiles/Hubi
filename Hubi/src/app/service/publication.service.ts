import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Publicacion } from '../models/general.model';

@Injectable({
  providedIn: 'root'
})
export class Publicaction {
  private urlPublicaciones = 'http://localhost:8090/publicaciones';

  constructor(private httpClient: HttpClient) { }

  getPublicaciones(categoria?: number): Observable<Publicacion[]> {
    let url = this.urlPublicaciones;
    if (categoria) {
      url += '?categoria=' + categoria;
    }
    return this.httpClient.get<Publicacion[]>(url);
  }

  createPublicacion(publicacion: Publicacion, imagen: File | null): Observable<Publicacion> {
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
