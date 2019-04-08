import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Theme, ThemeService } from './theme.service';
import { LoginService } from '../../auth/login.service';

@Component({
    templateUrl: 'theme-form.component.html',
})
export class ThemeFormComponent {
    
    newTheme: boolean;
    theme: Theme;

    constructor(private router: Router, activatedRoute: ActivatedRoute, private service: ThemeService, public loginService: LoginService,) {
        
        const content = activatedRoute.snapshot.params['content'];
        if (content) {
            service.getTheme(content).subscribe((theme) => (this.theme = theme), (error) => console.error(error));
            this.newTheme = false;
        } else {
            this.theme = { contenido: '', obras: null ,numObras: 0 };
            this.newTheme = true;
        }
    }

    cancel() {
        window.history.back();
    }

    save() {
        if(this.newTheme){
            this.service.saveTheme(this.theme).subscribe(
                _ => {},
                (error: Error) => console.error('Error creating new theme: ' + error),
            );
        }else{
            this.service.editTheme(this.theme).subscribe(
                _ => {},
                (error: Error) => console.error('Error creating new theme: ' + error),
            );
        }
        this.router.navigate(['/theme',this.theme.contenido]);
    }
}
