import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LoginService } from '../../auth/login.service';


export interface Author {
  id?: number;
  nombre: string;
  url_foto: string;
  fecha_nac: string;
  fecha_def: string;
  url_mapa: string;
  lugar: string;
}

const URL = '/api/autores/';
@Injectable()
export class AuthorService {

  constructor(private loginService: LoginService, private http: HttpClient) { }

  getAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(URL, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  getAuthor(title: string): Observable<Author> {
    return this.http.get<Author>(URL + title, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  saveAuthor(author: Author): Observable<Author> {

    const body = JSON.stringify(author);

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    if (!author.id) {
      return this.http
          .post<Author>(URL, body, { headers })
          .pipe(catchError((error) => this.handleError(error)));
    } else {
      return this.http
          .put<Author>(URL, body, { headers })
          .pipe(catchError((error) => this.handleError(error)));
    }
  }

  removeAuthor(author: Author): Observable<Author> {
    return this.http
        .delete<Author>(URL + author.id)
        .pipe(catchError((error) => this.handleError(error)));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error);
  }
}
