import { RouterModule } from '@angular/router';

import { BookListComponent } from './book-list.component';
import { BookDetailComponent } from './book-detail.component';
import { BookFormComponent } from './book-form.component';

//ours
import { IndexComponent } from './index.component';
import { ThemeDetailComponent } from './pages/theme/theme-detail.component';
import { ThemeFormComponent } from './pages/theme/theme-form.component';
import { ThemeListComponent } from './pages/theme/theme-list.component';
import { WorkFormComponent } from './pages/literaryWork/work-form.component';
import { WorkDetailComponent } from './pages/literaryWork/work-detail.component';
import { WorkListComponent } from './pages/literaryWork/work-list.component';
import { AuthorFormComponent } from './pages/author/author-form.component';
import { AuthorDetailComponent } from './pages/author/author-detail.component';
import { AuthorListComponent } from './pages/author/author-list.component';


const appRoutes = [
/*   { path: 'books', component: BookListComponent, useAsDefault: true },
  { path: 'book/new', component: BookFormComponent },
  { path: 'book/:id', component: BookDetailComponent },
  { path: 'book/edit/:id', component: BookFormComponent },
  { path: '', redirectTo: 'books', pathMatch: 'full' }, */

  { path: '', component: IndexComponent, useAsDefault: true },
  { path: 'theme/new', component: ThemeFormComponent },
  { path: 'theme/:content', component: ThemeDetailComponent },
  { path: 'theme/edit/:content', component: ThemeFormComponent },
  { path: 'themes', component: ThemeListComponent },
  { path: 'work/new', component: WorkFormComponent },
  { path: 'work/:title', component: WorkDetailComponent },
  { path: 'work/edit/:title', component: WorkFormComponent },
  { path: 'works', component: WorkListComponent },
  { path: 'author/new', component: AuthorFormComponent },
  { path: 'author/:name', component: AuthorDetailComponent },
  { path: 'author/edit/:nombre', component: AuthorFormComponent },
  { path: 'authors', component: AuthorListComponent },
  { path: '', redirectTo: '', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
