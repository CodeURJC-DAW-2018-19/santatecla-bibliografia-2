import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Author, AuthorService } from './author.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';

@Component({
  selector: 'author-component',
  templateUrl: './author.component.html'
})
export class AuthorComponent {
  author: Author;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: AuthorService, public loginService: LoginService, private _dialogService: TdDialogService) {
    const id = activatedRoute.snapshot.params['id'];
    service.getAuthor(id).subscribe((author) => (this.author = author), (error) => console.error(error));
  }

  removeAuthor() {

    this._dialogService.openConfirm({
      message: 'Do you want to remove this author?',
      title: 'Confirm',
      width: '500px',
      height: '175px'
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.service
            .removeAuthor(this.author)
            .subscribe((_) => this.router.navigate(['/']), (error) => console.error(error));
      }
    });
  }

  editAuthor() {
    //Editar el autor y luego navegar a ver ese autor, no mandar a una url para editar, se edita aqui mismo
    this.router.navigate(['/author/edit', this.author.id]);
  }

  gotoHome() {
    this.router.navigate(['/']);
  }

}
