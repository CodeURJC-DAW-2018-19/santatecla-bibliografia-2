import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../auth/login.service';
import { Author, AuthorService } from '../author/author.service';
import { Theme, ThemeService } from '../theme/theme.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  private authors: Author[];
  private themes: Theme[];

  constructor(private router: Router, private authorService: AuthorService, private themeService: ThemeService, public loginService: LoginService) { }

  ngOnInit() {
    this.authorService.getAuthors().subscribe(
        author => this.authors = author,
        error => console.log(error)
    );
    this.themeService.getThemes().subscribe(
        theme => this.themes = theme,
        error => console.log(error)
    );
  }

  newAuthor() {
    this.router.navigate(['/author/new']);
  }

  newWork() {
    this.router.navigate(['/work/new']);
  }

  newTheme() {
    this.router.navigate(['/theme/new']);
  }


}
