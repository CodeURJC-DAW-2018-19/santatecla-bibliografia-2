import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Theme, ThemeService } from './theme.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';
import { Quote } from '../literaryWork/work.service';
import { Author } from '../author/author.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
    templateUrl: 'theme-detail.component.html',
    styleUrls: ['./theme-list.component.css']
})
export class ThemeDetailComponent {
    theme: Theme;
    quotes: Quote[];
    authors: Author[];
    pdfURL: SafeResourceUrl;
    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        public service: ThemeService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
        private _domSanitizer: DomSanitizer,

    ) {
        const content = activatedRoute.snapshot.params['content'];
        service.getQuotes(content).subscribe(quotes => this.quotes = quotes, error => console.error(error));
        service.getTheme(content).subscribe((theme) => (this.theme = theme), (error) => console.error(error));
        service.getAuthors(content).subscribe((authors) => (this.authors = authors), (error) => console.error(error));

    }

    removeTheme() {

        this._dialogService.openConfirm({
            message: 'Do you want to remove this theme?',
            title: 'Confirm',
            width: '500px',
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.service
                    .removeTheme(this.theme)
                    .subscribe((_) => this.router.navigate(['']), (error) => console.error(error));
            }
        });
    }

    editTheme() {
        this.router.navigate(['/theme/edit', this.theme.contenido]);
    }

    gotoThemes() {
        this.router.navigate(['/themes']);
    }

    generatePDF() {
        this.service.downloadPDF(this.theme.contenido).subscribe(
            data => this.pdfURL = this.parseBlobToUrl(data),
            error => console.error(error)
        );
        var fileURL = URL.createObjectURL(this.pdfURL);
        window.open(fileURL);
    }

    parseBlobToUrl(data: Blob) {
        const url = URL.createObjectURL(data);
        return this._domSanitizer.bypassSecurityTrustResourceUrl(url)
    }

    cancel() {
        window.history.back();
    }
}
