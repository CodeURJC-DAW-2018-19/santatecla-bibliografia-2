import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs'; 
import { catchError } from 'rxjs/operators';
import { LoginService } from '../../auth/login.service';
import { Author } from "../author/author.service";
import { Theme } from "../theme/theme.service"

export interface Work {
  id?: number; 
  title: string; 
  URL: string; 
  date: string; 
  editorial: string; 
  url_editorial: string;
  citas: Array<Quote>;
  autores: Array<Author>;
}

export interface Quote {
  id?: number;
  contenido : string; 
}

const URL='/api/obras/';
@Injectable()
export class WorkService {

  constructor(private loginService: LoginService, private http: HttpClient) { }
  
  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error);
  }
// LITERARY WORK: 
  getWorks(): Observable<Work[]> {
    return this.http.get<Work[]>(URL, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }
  

  getWorksPageable(page:number): Observable<Work[]> {
    return this.http.get<Work[]>(URL + 'page/' + page, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }

  getWork(title: string): Observable<Work> {
    return this.http.get<Work>(URL + title, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
  }


  saveWork(work: Work, author: Author, theme: Theme): Observable<Work> {
 //AddObra & EditObra
    const body = JSON.stringify(work);

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    if (!work.id) {
      return this.http
          .post<Work>(URL +'/'+ author.nombre +'/'+ theme.contenido, body, { headers })
          .pipe(catchError((error) => this.handleError(error)));
    } else {
      return this.http
          .put<Work>(URL, body, { headers })
          .pipe(catchError((error) => this.handleError(error)));
    }
  }
 
   saveWorkOnly(work: Work): Observable<Work> {
 //AddObra & EditObra
    const body = JSON.stringify(work);

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    if (!work.id) {
      return this.http
          .post<Work>(URL  , body, { headers })
          .pipe(catchError((error) => this.handleError(error)));
    } else {
      return this.http
          .put<Work>(URL, body, { headers })
          .pipe(catchError((error) => this.handleError(error)));
    }
  }

  editWorkLinkAuthor(work: Work, author: Author): Observable<Work> {
    const body = JSON.stringify(work);

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http
          .put<Work>('/api/obras/autor/'+work.id + author.id, body, { headers })
          .pipe(catchError((error) => this.handleError(error)));

  }

  removeWork(work: Work): Observable<Work> {
    return this.http
        .delete<Work>(URL + work.title)
        .pipe(catchError((error) => this.handleError(error)));
  }

  removeWorkInAuthor(work: Work): Observable<Work> {
    return this.http
        .delete<Work>('/api/obra/' + work.title +'/borrar/autor')
        .pipe(catchError((error) => this.handleError(error)));
  }

  removeWorkInTheme(work: Work): Observable<Work> {
    return this.http
        .delete<Work>('/api/obra/' + work.title +'/borrar/tema')
        .pipe(catchError((error) => this.handleError(error)));
  }

  // addQuote in Literary Work 
  addQuote(quote: Quote, work: Work): Observable<Quote> {
    const body = JSON.stringify(work);

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    if (!work.id) {
      return this.http
          .post<Quote>('/api/obras/cita/'+work.id, body, { headers })
          .pipe(catchError((error) => this.handleError(error)));
    } 
  }

  downloadCoverImg(title:string):Observable<Blob>{
    return this.http
	  .get(URL+title+"/image/portada", {responseType: 'blob'})
	  .pipe(catchError((error) => this.handleError(error)));
  }

  downloadEditorialImg(title:string):Observable<Blob>{
    return this.http
	  .get(URL+title+"/image/editorial", {responseType: 'blob'})
	  .pipe(catchError((error) => this.handleError(error)));
  }

  uploadCoverImg(title:string, image: File): Observable<Object> {
    const HttpUploadOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'multipart/form-data' })
    }
    const formData = new FormData();
    formData.append("file", image);

    return this.http
    .post(URL+title+"/image/portada", formData)
    .pipe(catchError((error) => this.handleError(error)));
  }

  uploadEditorialImg(title:string, image: File): Observable<Object> {
    const HttpUploadOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'multipart/form-data' })
    }
    const formData = new FormData();
    formData.append("file", image);

    return this.http
    .post(URL+title+"/image/editorial", formData)
    .pipe(catchError((error) => this.handleError(error)));
  }

  getThemes(title: string): Observable<Theme[]> {
    return this.http
      .get<Theme[]>(URL + title + "/temas")
      .pipe(catchError((error) => this.handleError(error)));
  }

  getAuthors(title: string): Observable<Author[]> {
    return this.http
      .get<Author[]>(URL + title + "/autores")
      .pipe(catchError((error) => this.handleError(error)));
  }

  getQuotes(title: string): Observable<Quote[]> {
    return this.http
      .get<Quote[]>(URL + title + "/citas")
      .pipe(catchError((error) => this.handleError(error)));
  }

}
