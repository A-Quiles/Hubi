import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class General{

  private url = 'http://localhost:8090/usuario';
  private urlLogin = 'http://localhost:8090/usuario/login';

  constructor(private httpClient: HttpClient) { }

  createUser(data: any) {
    return this.httpClient.post(this.url, data);
  }
  loginUser(data: any) {
    return this.httpClient.post(this.urlLogin, data);
  }
}