import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Work, WorkService } from './work.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';

// SHOW WORK
@Component({
    selector: 'work-D-component',
    templateUrl: 'work-detail.component.html',
})
export class WorkDetailComponent {
    work: Work;

    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        public service: WorkService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
    ) {
        const title = activatedRoute.snapshot.params['title'];
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
                    .subscribe((_) => this.router.navigate(['/works']), (error) => console.error(error));
            }
        });
    }

    editWork() {
        this.router.navigate(['/work/edit', this.work.id]);
    }

    gotoWorks() {
        this.router.navigate(['/works']);
    }
}
