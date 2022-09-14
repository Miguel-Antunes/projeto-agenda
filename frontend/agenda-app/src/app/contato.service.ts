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
    private http: HttpClient
  ) { }

  apiUrl: string = environment.apiUrl + '/api/contatos'

  novoContato(contato: Contato): Observable<Contato> {
    return this.http.post<Contato>(this.apiUrl, contato)

  }
  listarTodos(): Observable<Contato[]> {
    return this.http.get<any>(this.apiUrl);
  }

  favoritarContato(contato: Contato): Observable<any> {
    return this.http.patch(this.apiUrl + '/' + contato.id + '/favorito', null)
  }
  uploadFoto(contato: Contato, formData: FormData): Observable<any> {
    return this.http.put(this.apiUrl + '/' + contato.id + '/foto', formData, { responseType: 'blob' })
  }
}
