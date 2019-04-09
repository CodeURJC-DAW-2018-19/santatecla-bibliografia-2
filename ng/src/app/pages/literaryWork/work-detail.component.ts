import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Work, WorkService , Quote} from './work.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Theme } from '../theme/theme.service';
import { Author } from '../author/author.service';

// SHOW WORK
@Component({
    selector: 'work-D-component',
    templateUrl: 'work-detail.component.html',
    styleUrls: ['./work-list.component.css']
})
export class WorkDetailComponent {
    work: Work;
    editorial: File;
    urlCover: SafeResourceUrl;
    urlEditorial: SafeResourceUrl;
    themes: Theme[];
    quotes: Quote[];
    authors: Author[];

    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        public service: WorkService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
        private _domSanitizer: DomSanitizer,
    ) {
        const title = activatedRoute.snapshot.params['title'];
        service.downloadCoverImg(title).subscribe(blobImg => this.urlCover = this.parseBlobToUrl(blobImg), error => console.error(error));
        service.downloadEditorialImg(title).subscribe(blobImg => this.urlEditorial = this.parseBlobToUrl(blobImg), error => console.error(error));
        service.getWork(title).subscribe((work) => (this.work = work), (error) => console.error(error));
        service.getThemes(title).subscribe((theme) => (this.themes = theme), (error) => console.error(error));
        service.getQuotes(title).subscribe((quotes) => (this.quotes = quotes), (error) => console.error(error));
        service.getAuthors(title).subscribe((authors) => (this.authors = authors), (error) => console.error(error));
    }

    removeWork() {

        this._dialogService.openConfirm({
            message: 'Do you want to remove this work?',
            title: 'Confirm', 
            width: '500px', 
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.service
                    .removeWork(this.work)
                    .subscribe((_) => this.router.navigate(['']), (error) => console.error(error));
            }
        });
    }

    editWork() {
        this.router.navigate(['/work/edit', this.work.title]);
    }

    addQuote() {
        this.router.navigate(['/work/cita', this.work.title]);
    }

    gotoWorks() {
        this.router.navigate(['/works']);
    }

    parseBlobToUrl(data: Blob) {
        const url= URL.createObjectURL(data);
        return this._domSanitizer.bypassSecurityTrustResourceUrl(url)
    }

    processCoverFile(imageInput: any) {
        const file: File = imageInput.files[0];
        const reader = new FileReader();
        reader.addEventListener('load', (event: any) => {
            
            this.service.uploadCoverImg(this.work.title, file).subscribe(
                (res) => {

                },
                (err) => {

                })
        });

        reader.readAsDataURL(file);
    }

    cancel() {
        window.history.back();
    }

}
