import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Work, WorkService } from './work.service';

@Component({
    templateUrl: 'work-form.component.html',
})
export class WorkFormComponent {
    
    newWork: boolean;
    work: Work;

    constructor(private _router: Router, activatedRoute: ActivatedRoute, private service: WorkService) {
        const id = activatedRoute.snapshot.params['id'];
        if (id) {
            service.getWork(id).subscribe((work) => (this.work = work), (error) => console.error(error));
            this.newWork = false;
        } else {
            this.work = {  title: '', URL: '', date: '', editorial: '', url_editorial: ''};
            this.newWork = true;
        }
    }

    cancel() {
        window.history.back();
    }

/*     save() {
        this.service.saveWork(this.work, this.author, this.theme).subscribe(
            _ => {},
            (error: Error) => console.error('Error creating new work: ' + error),
        );
        window.history.back();
    } */
}
