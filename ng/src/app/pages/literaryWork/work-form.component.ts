import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Work, WorkService } from './work.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';
import { Author, AuthorService } from '../author/author.service';
import { Theme, ThemeService } from '../theme/theme.service';
import {MatCheckboxModule} from '@angular/material/checkbox';

//Create new work
@Component({
    selector: 'work-F-component',
    templateUrl: './work-form.component.html',
})
export class WorkFormComponent {
    
    newWork: boolean;
    work: Work;
    authors: Author[];
    themes: Theme[];
    author: Author;
    theme: Theme;

    constructor(private _router: Router, activatedRoute: ActivatedRoute, private service: WorkService, private authorService: AuthorService, private themeService: ThemeService
        //, public loginService: LoginService, private _dialogService: TdDialogService
        ) {
        /*const title = activatedRoute.snapshot.params['title'];
        if (title) {
            service.getWork(title).subscribe((work) => (this.work = work), (error) => console.error(error));
            //HERE ADD THIS.ATHOR & THIS.THEME BUT IDK
            this.newWork = false;*/
        const id = activatedRoute.snapshot.params['id'];
        if (id) {
            service.getWork(id).subscribe((work) => (this.work = work), (error) => console.error(error));
            
            this.newWork = false;
        } else {
            this.work = {  title: '', URL: '', date: '', editorial: '', url_editorial: ''};
        
            this.newWork = true;
        }
    }

    ngOnInit() {
    this.authorService.getAuthors().subscribe(
      authors => this.authors = authors,
      error => console.log(error)
    );
    this.themeService.getThemes().subscribe(
      themes => this.themes = themes,
      error => console.log(error)
    );
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
