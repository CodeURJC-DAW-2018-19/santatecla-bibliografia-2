import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './auth/login.service';
import { Author, AuthorService } from './pages/author/author.service';
import { Theme, ThemeService } from './pages/theme/theme.service';
import { Work, WorkService } from './pages/literaryWork/work.service';


@Component({
  selector: 'app-index',
  templateUrl: './index.html',
})
export class IndexComponent{

  constructor(private router: Router, public loginService: LoginService) { }

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
