import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Book, BookService } from './book.service';
import { LoginService } from './auth/login.service';
import { TdDialogService } from '@covalent/core';

@Component({
    templateUrl: 'book-detail.component.html',
})
export class BookDetailComponent {
    book: Book;

    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        public service: BookService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
    ) {
        const id = activatedRoute.snapshot.params['id'];
        service.getBook(id).subscribe((book) => (this.book = book), (error) => console.error(error));
    }

    removeBook() {

        this._dialogService.openConfirm({
            message: 'Do you want to remove this book?',
            title: 'Confirm', 
            width: '500px', 
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.service
                    .removeBook(this.book)
                    .subscribe((_) => this.router.navigate(['/books']), (error) => console.error(error));
            }
        });
    }

    editBook() {
        this.router.navigate(['/book/edit', this.book.id]);
    }

    gotoBooks() {
        this.router.navigate(['/books']);
    }
}
