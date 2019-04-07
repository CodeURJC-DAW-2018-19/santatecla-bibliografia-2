import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Author, AuthorService } from './author.service';
import { LoginService } from '../../auth/login.service';

@Component({
  selector: 'authors',
  templateUrl: 'author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements OnInit {

  authors: Author[];

  constructor(private router: Router, private service: AuthorService,
    public loginService: LoginService) { }

  ngOnInit() {
    this.service.getAuthorsPageable().subscribe(
      authors => this.authors = authors,
      error => console.log(error)
    );
  }

  newAuhtor() {
    this.router.navigate(['/author/new']);
  }
}
