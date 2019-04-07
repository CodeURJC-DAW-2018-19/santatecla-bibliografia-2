import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Author, AuthorService } from './author.service';
import { Work, WorkService} from '../literaryWork/work.service';

@Component({
    templateUrl: 'author-form.component.html',
})
export class AuthorFormComponent {
    
    newAuthor: boolean;
    author: Author;
    work: Work;
    works: Work [];
    constructor(private _router: Router, activatedRoute: ActivatedRoute, private service: AuthorService, private workService: WorkService) {
        const nombre = activatedRoute.snapshot.params['nombre'];
        if (nombre) {
            service.getAuthor(nombre).subscribe((author) => (this.author = author), (error) => console.error(error));
            this.newAuthor = false;
        } else {
            this.author = { nombre: '', url_foto: '', fecha_nac: '', fecha_def: '', url_mapa: '', lugar: '' };
            this.newAuthor = true;
        }
    }
    ngOnInit(){
    this.workService.getWorks().subscribe(
        works => this.works = works,
        error => console.log(error)
    );
    }
    cancel() {
        window.history.back();
    }

    save() {
        this.service.saveAuthor(this.author, this.work).subscribe(
            _ => {},
            (error: Error) => console.error('Error creating new author: ' + error),
        );
        window.history.back();
    }
}
