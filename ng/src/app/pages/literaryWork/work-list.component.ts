import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Work, WorkService } from './work.service';
import { LoginService } from '../../auth/login.service';

@Component({
  selector: 'works',
  templateUrl: 'work-list.component.html'
})
export class WorkListComponent implements OnInit {

  works: Work[];

  constructor(private router: Router, private service: WorkService,
    public loginService: LoginService) { }

  ngOnInit() {
    this.service.getWorks().subscribe(
      works => this.works = works,
      error => console.log(error)
    );
  }

  newWork() {
    this.router.navigate(['/work/new']);
  }
}
