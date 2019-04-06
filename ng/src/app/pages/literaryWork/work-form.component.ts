import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Work, WorkService } from './work.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';
import { Author } from '../author/author.service';
import { Theme } from '../theme/theme.service';

//Create new work
@Component({
    selector: 'work-formC',
    templateUrl: 'work-form.component.html',
})
export class WorkFormComponent {
    
    newWork: boolean;
    work: Work;
    author: Author;
    theme: Theme;

    constructor(private _router: Router, activatedRoute: ActivatedRoute, private service: WorkService, public loginService: LoginService, private _dialogService: TdDialogService) {
        /*const title = activatedRoute.snapshot.params['title'];
        if (title) {
            service.getWork(title).subscribe((work) => (this.work = work), (error) => console.error(error));
            //HERE ADD THIS.ATHOR & THIS.THEME BUT IDK
            this.newWork = false;*/
        const id = activatedRoute.snapshot.params['id'];
        if (id) {
            service.getWork(id).subscribe((work) => (this.work = work), (error) => console.error(error));
            //HERE ADD THIS.ATHOR & THIS.THEME BUT IDK
            this.newWork = false;
        } else {
            this.work = {  title: '', URL: '', date: '', editorial: '', url_editorial: ''};
            //HERE ADD THIS.ATHOR & THIS.THEME BUT IDK
            this.newWork = true;
        }
    }

    cancel() {
        window.history.back();
    }


    save() {
        this.service.saveWork(this.work,this.author,this.theme).subscribe(
            _ => {},
            (error: Error) => console.error('Error creating new work: ' + error),
        );
        window.history.back();
      }
}
