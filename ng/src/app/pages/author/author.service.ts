import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LoginService } from '../../auth/login.service';
import { Theme } from "../theme/theme.service"
import { Work, Quote } from "../literaryWork/work.service"
import { temporaryAllocator } from '@angular/compiler/src/render3/view/util';

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

  constructor(private loginService: LoginService, private http: HttpClient) {}

  getAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(URL, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  getAuthorsPageable(page:number): Observable<Author[]> {
    return this.http.get<Author[]>(URL + 'page/' + page, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  getAuthor(nombre: string): Observable<Author> {
    return this.http.get<Author>(URL + nombre, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  saveAuthor(author: Author,work: Work): Observable<Author> {

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

  saveAuthorOnly(author: Author): Observable<Author> {

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
        .delete<Author>(URL + author.nombre)
        .pipe(catchError((error) => this.handleError(error)));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error);
  }

  getShowThemeInAuthor(author: Author, theme: Theme): Observable<Author> {
    return this.http.get<Author>(URL + 'temas/'+theme.contenido, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  removeThemeInAuthor(author: Author, theme: Theme): Observable<Author> {
    return this.http
        .delete<Author>(URL +'temas/'+ theme.contenido)
        .pipe(catchError((error) => this.handleError(error)));
  }

  getShowWorkInAuthor(author: Author, work:Work): Observable<Author> {
    return this.http.get<Author>(URL + 'obras/'+work.title, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  getQuotes(name: string): Observable<Quote[]> {
    return this.http
      .get<Quote[]>(URL + name + "/citas")
      .pipe(catchError((error) => this.handleError(error)));
  }

  getWorks(name: string): Observable<Work[]> {
    return this.http
      .get<Work[]>(URL + name + "/obras")
      .pipe(catchError((error) => this.handleError(error)));
  }

  getThemes(name: string): Observable<Theme[]> {
    return this.http
      .get<Theme[]>(URL + name + "/temas")
      .pipe(catchError((error) => this.handleError(error)));
  }
  
  //getShowQuoteInAuthor(author: Author, quote:Quote ): Observable<Author> {
  //  return this.http.get<Author>(URL + 'citas/'+quote.id, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  //}
  /*removeQuoteInAuthor(author: Author, quote: Quote): Observable<Author> {
    return this.http
        .delete<Author>(URL +'citas/'+ quote.id)
        .pipe(catchError((error) => this.handleError(error)));
  }*/
}
