import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Work, WorkService } from './work.service';
import { LoginService } from '../../auth/login.service';
import { TdDialogService } from '@covalent/core';

@Component({
  selector: 'work-show-component',
  templateUrl: './work-show.component.html'
})
export class WorkShowComponent {
  work: Work;

  constructor(private router: Router, activatedRoute: ActivatedRoute, public service: WorkService, public loginService: LoginService, private _dialogService: TdDialogService) {
    const id = activatedRoute.snapshot.params['id'];
    service.getWork(id).subscribe((work) => (this.work = work), (error) => console.error(error));
  }

  
  removeWork() {
    this._dialogService.openConfirm({
      message: 'Do you want to remove this Work?',
      title: 'Confirm',
      width: '500px',
      height: '175px'
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.service
            .removeWork(this.work)
            .subscribe((_) => this.router.navigate(['/']), (error) => console.error(error));
      }
    });
  }
  updateWork() {
    this.router.navigate(['/work/update', this.work.id]);
  }

}