import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Author, AuthorService } from './author.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';

@Component({
    templateUrl: 'author-detail.component.html',
})
export class AuthorDetailComponent {
    author: Author;

    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        public service: AuthorService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
    ) {
        const name = activatedRoute.snapshot.params['name'];
        service.getAuthor(name).subscribe((author) => (this.author = author), (error) => console.error(error));
    }

    removeAuthor() {

        this._dialogService.openConfirm({
            message: 'Do you want to remove this author?',
            title: 'Confirm', 
            width: '500px', 
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.service
                    .removeAuthor(this.author)
                    .subscribe((_) => this.router.navigate(['']), (error) => console.error(error));
            }
        });
    }

    editAuthor() {
        this.router.navigate(['/author/edit', this.author.id]);
    }

    gotoAuthors() {
        this.router.navigate(['']);
    }
}
