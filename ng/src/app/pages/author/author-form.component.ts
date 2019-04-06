import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Author, AuthorService } from './author.service';

@Component({
    templateUrl: 'author-form.component.html',
})
export class AuthorFormComponent {
    
    newAuthor: boolean;
    author: Author;

    constructor(private _router: Router, activatedRoute: ActivatedRoute, private service: AuthorService) {
        const id = activatedRoute.snapshot.params['id'];
        if (id) {
            service.getAuthor(id).subscribe((author) => (this.author = author), (error) => console.error(error));
            this.newAuthor = false;
        } else {
            this.author = { nombre: '', url_foto: '', fecha_nac: '', fecha_def: '', url_mapa: '', lugar: '' };
            this.newAuthor = true;
        }
    }
    nombre: string;
    url_foto: string;
    fecha_nac: string;
    fecha_def: string;
    url_mapa: string;
    lugar: string;
    cancel() {
        window.history.back();
    }

    save() {
        this.service.saveAuthor(this.author).subscribe(
            _ => {},
            (error: Error) => console.error('Error creating new author: ' + error),
        );
        window.history.back();
    }
}
