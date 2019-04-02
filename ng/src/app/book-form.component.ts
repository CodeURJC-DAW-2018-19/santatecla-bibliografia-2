import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Book, BookService } from './book.service';

@Component({
    templateUrl: 'book-form.component.html',
})
export class BookFormComponent {
    
    newBook: boolean;
    book: Book;

    constructor(private _router: Router, activatedRoute: ActivatedRoute, private service: BookService) {
        const id = activatedRoute.snapshot.params['id'];
        if (id) {
            service.getBook(id).subscribe((book) => (this.book = book), (error) => console.error(error));
            this.newBook = false;
        } else {
            this.book = { title: '', description: '' };
            this.newBook = true;
        }
    }

    cancel() {
        window.history.back();
    }

    save() {
        this.service.saveBook(this.book).subscribe(
            _ => {},
            (error: Error) => console.error('Error creating new book: ' + error),
        );
        window.history.back();
    }
}
