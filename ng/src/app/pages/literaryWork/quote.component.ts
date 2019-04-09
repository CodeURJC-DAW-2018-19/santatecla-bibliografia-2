import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../../auth/login.service';

import { Quote, Work, WorkService } from './work.service';
import {FormControl} from '@angular/forms';
@Component({
    templateUrl: 'quote.component.html',
})
export class QuoteComponent {
    
    quote : Quote;
    work : Work;
    myControl = new FormControl();

    constructor(private router: Router, activatedRoute: ActivatedRoute, private service: WorkService , public loginService: LoginService,) {
        
        const title = activatedRoute.snapshot.params['title'];
        if (title) {
            service.getWork(title).subscribe((work) => (this.work = work), (error) => console.error(error));
            this.quote = { contenido: ''}
        }
    }

    cancel() {
        window.history.back();
    }

    save() {
        this.service.addQuote(this.quote,this.work).subscribe(
                _ => {},
                (error: Error) => console.error('Error creating new quote: ' + error),
            );
        this.router.navigate(['/work',this.work.title]);
    }
}