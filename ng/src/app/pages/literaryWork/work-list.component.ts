import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Work, WorkService } from './work.service';
import { LoginService } from '../../auth/login.service';

//SHOW ALL WORKS 
@Component({
  selector: 'works',
  templateUrl: 'work-list.component.html'
})
export class WorkListComponent implements OnInit {

  works: Work[];

  constructor(private router: Router, private service: WorkService,
    public loginService: LoginService) { }

  ngOnInit() {
    this.service.getWorksPageable().subscribe(
      works => this.works = works,
      error => console.log(error)
    );
  }

  newWork() {
    this.router.navigate(['/work/new']);
  }
}
