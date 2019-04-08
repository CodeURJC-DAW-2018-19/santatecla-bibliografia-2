import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Author, AuthorService } from './author.service';
import { Work, WorkService, Quote} from '../literaryWork/work.service';
import {FormControl} from '@angular/forms';
import { LoginService } from '../../auth/login.service';
import { Theme } from '../theme/theme.service';

@Component({
    templateUrl: 'author-form.component.html',
    styleUrls: ['./author-list.component.css']
})
export class AuthorFormComponent {
    
    newAuthor: boolean;
    author: Author;
    work: Work;
    themes: Theme[];
    quotes: Quote[];
    works: Work[];
    constructor(private _router: Router, activatedRoute: ActivatedRoute, private service: AuthorService,public loginService: LoginService, private workService: WorkService) {
        const nombre = activatedRoute.snapshot.params['nombre'];
        if (nombre) {
            service.getAuthor(nombre).subscribe((author) => (this.author = author), (error) => console.error(error));
            service.getThemes(nombre).subscribe((themes) => (this.themes = themes), (error) => console.error(error));
            service.getQuotes(nombre).subscribe(quotes => this.quotes = quotes, error =>console.error(error));
            service.getWorks(nombre).subscribe(works => this.works = works, error =>console.error(error));
            this.newAuthor = false;
        } else {
            this.author = { nombre: '', url_foto: '', fecha_nac: '', fecha_def: '', url_mapa: '', lugar: '' };
            this.newAuthor = true;
        }
    }

    cancel() {
        window.history.back();
    }

    save() {
        //this.service.saveAuthor(this.author, this.work).subscribe(
        //    _ => {},
        //    (error: Error) => console.error('Error creating new author: ' + error),
        //);
        this.service.saveAuthorOnly(this.author).subscribe(
            _ => {},
            (error: Error) => console.error('Error creating new author: ' + error),
        );
        window.history.back();
    }
}
