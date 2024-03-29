import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Theme, ThemeService } from './theme.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';
import { PageEvent, MatPaginatorModule} from '@angular/material';
import { TabService } from '../../tabs/tab.service';


@Component({
  selector: 'themes',
  templateUrl: 'theme-list.component.html',
  styleUrls: ['./theme-list.component.css']

})
export class ThemeListComponent implements OnInit {

  themes: Theme[];
  pageEvent: PageEvent;


  constructor(private router: Router, private service: ThemeService, 
        private _dialogService: TdDialogService,
    public loginService: LoginService,
    public tabService: TabService
    ) {}

  ngOnInit() {
    this.service.getThemesPageable(0).subscribe(
      themes => this.themes = themes,
      error => console.log(error)
    );
  }

  getPage(event?:PageEvent){
    this.service.getThemesPageable(event.pageIndex).subscribe(
      themes => this.themes = themes,
      error => console.log(error)
    );
  }

  newTheme() {
    this.router.navigate(['/theme/new']);
  }

  deleteTheme(theme:Theme){
    this._dialogService.openConfirm({
            message: 'Do you want to remove this theme?',
            title: 'Confirm', 
            width: '500px', 
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
              this.router.navigate(['/theme/'+theme.contenido]);
    this.service.removeTheme(theme).subscribe(() => this.router.navigate(['/']), (error) => console.error(error));
    }
        });
  }
}
