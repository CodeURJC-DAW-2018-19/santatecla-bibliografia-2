import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Author, AuthorService } from './author.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';
import { Quote, Work } from '../literaryWork/work.service';
import { Theme } from '../theme/theme.service';

@Component({
    templateUrl: 'author-detail.component.html',
    styleUrls: ['./author-list.component.css']
})
export class AuthorDetailComponent {
    author: Author;
    themes: Theme[];
    quotes: Quote[];
    works: Work[];


    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        public service: AuthorService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
    ) {
        const name = activatedRoute.snapshot.params['name'];
        service.getAuthor(name).subscribe((author) => (this.author = author), (error) => console.error(error));
        service.getThemes(name).subscribe((themes) => (this.themes = themes), (error) => console.error(error));
        service.getQuotes(name).subscribe(quotes => this.quotes = quotes, error =>console.error(error));
        service.getWorks(name).subscribe(works => this.works = works, error =>console.error(error));
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
        this.router.navigate(['/author/edit', this.author.nombre]);
    }

    gotoAuthors() {
        this.router.navigate(['/authors']);
    }
    cancel() {
        window.history.back();
    }
}
