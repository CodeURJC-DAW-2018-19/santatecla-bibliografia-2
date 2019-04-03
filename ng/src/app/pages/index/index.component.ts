import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../auth/login.service';
import { Author, AuthorService } from '../author/author.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  private authors: Author[];

  constructor(private router: Router, private authorService: AuthorService,
              public loginService: LoginService) { }

  ngOnInit() {
    this.authorService.getAuthors().subscribe(
        authors => this.authors = authors,
        error => console.log(error)
    );
  }

}
