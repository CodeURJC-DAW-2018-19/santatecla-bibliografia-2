import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LoginService } from './auth/login.service';

export interface Book {
    id?: number;
    title: string;
    description: string;
}

const URL = '/api/books/';
@Injectable()
export class BookService {
    constructor(private loginService: LoginService, private http: HttpClient) {}

    getBooks(): Observable<Book[]> {
        return this.http.get<Book[]>(URL, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
    }

    getBook(id: number | string): Observable<Book> {
        return this.http.get<Book>(URL + id, { withCredentials: true }).pipe(catchError((error) => this.handleError(error)));
    }

    saveBook(book: Book): Observable<Book> {

        const body = JSON.stringify(book);
        
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
        });

        if (!book.id) {
            return this.http
                .post<Book>(URL, body, { headers })
                .pipe(catchError((error) => this.handleError(error)));
        } else {
            return this.http
                .put<Book>(URL + book.id, body, { headers })
                .pipe(catchError((error) => this.handleError(error)));
        }
    }

    removeBook(book: Book): Observable<Book> {
        return this.http
            .delete<Book>(URL + book.id)
            .pipe(catchError((error) => this.handleError(error)));
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error);
    }
}
