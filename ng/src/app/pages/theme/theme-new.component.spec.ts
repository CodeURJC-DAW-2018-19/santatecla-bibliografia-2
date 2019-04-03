import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ThemeNewComponent } from './theme-new.component';

describe('ThemeNewComponent', () => {
  let component: ThemeNewComponent;
  let fixture: ComponentFixture<ThemeNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ThemeNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThemeNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
