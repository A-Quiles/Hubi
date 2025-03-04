import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categoria } from '../models/general.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private urlCategorias = 'http://localhost:8090/categorias';

  constructor(private httpClient: HttpClient) { }

  getCategorias(): Observable<Categoria[]> {
    return this.httpClient.get<Categoria[]>(this.urlCategorias);
  }
}
