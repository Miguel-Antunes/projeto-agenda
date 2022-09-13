import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Contato } from './contato/Contato';

@Injectable({
  providedIn: 'root'
})
export class ContatoService {

  constructor(
    private Http: HttpClient
  ) { }

  apiUrl: string = environment.apiUrl + '/api/contatos'

  novoContato(contato: Contato): Observable<Contato> {
    return this.Http.post<Contato>(this.apiUrl, contato)

  }
  listarTodos(): Observable<Contato[]> {
    return this.Http.get<any>(this.apiUrl);
  }

}
