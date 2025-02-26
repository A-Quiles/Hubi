import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Publicacion } from '../models/publicaction.model';

@Injectable({
  providedIn: 'root'
})
export class Publicaction {

  private urlPublicaciones = 'http://localhost:8090/publicaciones'; // URL del backend para publicaciones

  constructor(private httpClient: HttpClient) { }

  // Método para obtener todas las publicaciones
  getPublicaciones(): Observable<Publicacion[]> {
    return this.httpClient.get<Publicacion[]>(this.urlPublicaciones);
  }

  // Método para crear una nueva publicación
  createPublicacion(publicacion: Publicacion): Observable<Publicacion> {
    return this.httpClient.post<Publicacion>(this.urlPublicaciones, publicacion);
  }
}
