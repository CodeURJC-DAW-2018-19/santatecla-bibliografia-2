import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TdDialogService } from '@covalent/core';
import { Author, AuthorService } from './author.service';
import { LoginService } from '../../auth/login.service';

@Component({
  selector: 'authors',
  templateUrl: 'author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements OnInit {

  authors: Author[];

  constructor(private router: Router, private service: AuthorService,private _dialogService: TdDialogService,
    public loginService: LoginService) { }

  ngOnInit() {
    this.service.getAuthorsPageable().subscribe(
      authors => this.authors = authors,
      error => console.log(error)
    );
  }

  newAuthor() {
    this.router.navigate(['/author/new']);
  }

  deleteAuthor(author: Author){
    this._dialogService.openConfirm({
            message: 'Do you want to remove this author?',
            title: 'Confirm', 
            width: '500px', 
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
              this.router.navigate(['/author/'+author.nombre]);
    this.service.removeAuthor(author).subscribe(() => this.router.navigate(['/']), (error) => console.error(error));
    }
        });
  }
}
