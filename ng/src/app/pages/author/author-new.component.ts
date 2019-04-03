import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Author, AuthorService } from './author.service';

@Component({
  selector: 'app-author-new',
  templateUrl: './author-new.component.html'
})
export class AuthorNewComponent {

  newBook: boolean;
  author: Author;

  constructor(private _router: Router, activatedRoute: ActivatedRoute, private service: AuthorService) {
    const id = activatedRoute.snapshot.params['id'];
    if (id) {
      service.getAuthor(id).subscribe((author) => (this.author = author), (error) => console.error(error));
      this.newBook = false;
    } else {
      this.author = {fecha_def: "", fecha_nac: "", lugar: "", url_mapa: "", nombre: '', url_foto: '' };
      this.newBook = true;
    }
  }

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
