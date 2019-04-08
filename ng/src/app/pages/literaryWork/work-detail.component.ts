import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Work, WorkService } from './work.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

// SHOW WORK
@Component({
    selector: 'work-D-component',
    templateUrl: 'work-detail.component.html',
})
export class WorkDetailComponent {
    work: Work;
    editorial: File;
    //url: SafeResourceUrl;
    urlCover: SafeResourceUrl;

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
        service.getWork(title).subscribe((work) => (this.work = work), (error) => console.error(error));
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

    gotoWorks() {
        this.router.navigate(['']);
    }

    parseBlobToUrl(data: Blob) {
        const url= URL.createObjectURL(data);
        return this._domSanitizer.bypassSecurityTrustResourceUrl(url)
    }
    cancel() {
        window.history.back();
    }

}
