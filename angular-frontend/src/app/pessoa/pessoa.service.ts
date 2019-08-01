import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Pipe, PipeTransform } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Pessoa } from '@app/model/pessoa.model';
@Injectable({
  providedIn: 'root'
})
export class PessoaService {
  private pessoaUrl = 'http://localhost:8080/Pessoa'; // URL to web api
  private pessoaSoftDeleteUrl = 'http://localhost:8080/SoftDeletePessoa/'; // URL to web api

  private countPessoasFiltradasUrl = 'http://localhost:8080/CountFilterPage'; // URL to web api
  private getPessoasFiltradasUrl = 'http://localhost:8080/PessoasFilterPage'; // URL to web api

  constructor(private http: HttpClient) {}

  countPessoasFiltradas(
    nome: string,
    email: string,
    cpf: string
  ): Observable<number> {
    let parametros = new HttpParams();
    parametros = parametros.append('nome', nome);
    parametros = parametros.append('email', email);
    parametros = parametros.append('cpf', cpf);

    return this.http.get<number>(this.countPessoasFiltradasUrl, {
      params: parametros
    });
  }

  getPessoasFiltradas(
    pagina: number,
    quantidade: number,
    nome: string,
    email: string,
    cpf: string,
    ordem: string,
    direcao: string
  ): Observable<Pessoa[]> {
    let parametros = new HttpParams();
    parametros = parametros.append('pagina', pagina.toString());
    parametros = parametros.append('quantidade', quantidade.toString());
    parametros = parametros.append('nome', nome);
    parametros = parametros.append('email', email);
    parametros = parametros.append('cpf', cpf);
    parametros = parametros.append('ordem', ordem);
    parametros = parametros.append('direcao', direcao);

    return this.http.get<Pessoa[]>(this.getPessoasFiltradasUrl, {
      params: parametros
    });
  }

  getPessoa(id: number): Observable<Pessoa> {
    return this.http.get<Pessoa>(this.pessoaUrl + '/' + id).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  editPessoa(pessoa: Pessoa): Observable<Pessoa> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    console.log(pessoa);
    return this.http
      .put<Pessoa>(this.pessoaUrl + '/' + pessoa.id, pessoa, httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  deletePessoa(id: number): Observable<Pessoa[]> {
    return this.http.delete<Pessoa[]>(this.pessoaSoftDeleteUrl + id);
  }
  createPessoa(pessoa: Pessoa) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<Pessoa>(this.pessoaUrl, pessoa, httpOptions).pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
      console.log(error.error.errors);
    } else {
      // Get server-side error

      console.log(error.error.errors);

      if (error.error.errors) {
        error.error.errors.forEach((element: any) => {
          errorMessage += '\n' + element.defaultMessage;
        });
      } else {
        errorMessage = 'bad';
      }
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
