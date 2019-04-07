import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Theme, ThemeService } from './theme.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';

@Component({
    templateUrl: 'theme-detail.component.html',
})
export class ThemeDetailComponent{
    theme: Theme;
    quotes: string[];
    constructor(
        private router: Router,
        activatedRoute: ActivatedRoute,
        public service: ThemeService,
        public loginService: LoginService,
        private _dialogService: TdDialogService,
    ) {
        const content = activatedRoute.snapshot.params['content'];
        service.getQuotes(content).subscribe(quotes => this.quotes = quotes, error =>console.error(error));
        service.getTheme(content).subscribe((theme) => (this.theme = theme), (error) => console.error(error));
        
    }

    removeTheme() {

        this._dialogService.openConfirm({
            message: 'Do you want to remove this theme?',
            title: 'Confirm', 
            width: '500px', 
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.service
                    .removeTheme(this.theme)
                    .subscribe((_) => this.router.navigate(['/themes']), (error) => console.error(error));
            }
        });
    }

    editTheme() {
        this.router.navigate(['/theme/edit', this.theme.id]);
    }

    gotoThemes() {
        this.router.navigate(['/themes']);
    }

/*    ngOnInit(){
        this.service.getQuotes(this.theme.contenido).subscribe(
        quotes=> this.quotes = quotes,
        error => console.error(error)
    )} */
}
