import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Work, WorkService } from './work.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';

@Component({
  selector: 'work-component',
  templateUrl: './work.component.html'
})
export class WorkComponent {
  work: Work;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: WorkService, public loginService: LoginService, private _dialogService: TdDialogService) {
    const id = activatedRoute.snapshot.params['id'];
    service.getWork(id).subscribe((work) => (this.work = work), (error) => console.error(error));
  }
  


 

}
