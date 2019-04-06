import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Theme, ThemeService } from './theme.service';
import { LoginService } from '../../auth/login.service';

@Component({
  selector: 'themes',
  templateUrl: 'theme-list.component.html'
})
export class ThemeListComponent implements OnInit {

  themes: Theme[];

  constructor(private router: Router, private service: ThemeService,
    public loginService: LoginService) { }

  ngOnInit() {
    this.service.getThemes().subscribe(
      themes => this.themes = themes,
      error => console.log(error)
    );
  }

  newTheme() {
    this.router.navigate(['/theme/new']);
  }
}
