import { Injectable } from '@angular/core';
import {LoginService} from "../../auth/login.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {catchError} from "rxjs/operators";

export interface Theme {
  id?: number;
  obras: Array<Work>;
  contenido: string;
  numObras: number;
}

const URL = '/api/temas/';
@Injectable()
export class ThemeService {

  constructor(private loginService: LoginService, private http: HttpClient) { }

  getThemes(): Observable<Theme[]> {
    return this.http.get<Theme[]>(URL, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  getTheme(title: string): Observable<Theme> {
    return this.http.get<Theme>(URL + title, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  saveTheme(theme: Theme): Observable<Theme> {

    const body = JSON.stringify(theme);

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    if (!theme.id) {
      return this.http
          .post<Theme>(URL, body, { headers })
          .pipe(catchError((error) => this.handleError(error)));
    } else {
      return this.http
          .put<Theme>(URL + theme.id, body, { headers })
          .pipe(catchError((error) => this.handleError(error)));
    }
  }

  removeTheme(theme: Theme): Observable<Theme> {
    return this.http
        .delete<Theme>(URL + theme.id)
        .pipe(catchError((error) => this.handleError(error)));
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error);
  }
}
