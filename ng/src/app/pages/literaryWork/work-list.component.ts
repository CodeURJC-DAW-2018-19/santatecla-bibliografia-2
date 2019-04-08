import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Router } from '@angular/router';
import { TdDialogService } from '@covalent/core';
import { Work, WorkService } from './work.service';
import { LoginService } from '../../auth/login.service';
import { PageEvent, MatPaginatorModule} from '@angular/material';

//SHOW ALL WORKS 
@Component({
  selector: 'works',
  templateUrl: 'work-list.component.html',
  styleUrls: ['./work-list.component.css']
})
export class WorkListComponent implements OnInit {

  works: Work[];
  pageEvent: PageEvent;

  constructor(private router: Router, private service: WorkService,private _dialogService: TdDialogService,
    public loginService: LoginService) { }

  ngOnInit() {
    this.service.getWorksPageable(0).subscribe(
      works => this.works = works,
      error => console.log(error)
    );
  }

  getPage(event?:PageEvent){
    this.service.getWorksPageable(event.pageIndex).subscribe(
      works => this.works = works,
      error => console.log(error)
    );
  }

  newWork() {
    this.router.navigate(['/work/new']);
  }
  deleteWork(work: Work){
    this._dialogService.openConfirm({
            message: 'Do you want to remove this work?',
            title: 'Confirm', 
            width: '500px', 
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
              this.router.navigate(['/work/'+work.title]);
    this.service.removeWork(work).subscribe(() => this.router.navigate(['/']), (error) => console.error(error));
    }
        });
  }
}
