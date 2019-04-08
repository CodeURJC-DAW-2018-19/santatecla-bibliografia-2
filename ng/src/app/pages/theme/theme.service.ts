import { Injectable } from '@angular/core';
import {LoginService} from "../../auth/login.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";
import {Work} from "../literaryWork/work.service";

export interface Theme {
  id?: number;
  obras: Array<Work>;
  contenido: string;
  numObras: number;
}

const URL = '/api/temas/';
@Injectable()
export class ThemeService {

  constructor(private loginService: LoginService, private http: HttpClient) { 
  }

  getThemes(): Observable<Theme[]> {
    return this.http.get<Theme[]>(URL, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  getThemesPageable(page:number): Observable<Theme[]> {
    return this.http.get<Theme[]>(URL + 'page/' + page, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  getTheme(contenido: string): Observable<Theme> {
    return this.http.get<Theme>(URL + contenido, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  saveTheme(theme: Theme): Observable<Theme> {

    const body = JSON.stringify(theme);

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http
      .post<Theme>(URL, body, { headers })
      .pipe(catchError((error) => this.handleError(error)));
  }
  editTheme(theme: Theme): Observable<Theme> {

    const body = JSON.stringify(theme);

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http
      .put<Theme>(URL + theme.id, body, { headers })
      .pipe(catchError((error) => this.handleError(error)));
  }

  removeTheme(theme: Theme): Observable<Theme> {
    return this.http
        .delete<Theme>(URL + theme.contenido)
        .pipe(catchError((error) => this.handleError(error)));
  }

  getQuotes(contenido: string): Observable<any[]> {
    return this.http
      .get<any[]>(URL + contenido + "/citas")
      .pipe(catchError((error) => this.handleError(error)));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error);
  }
}
