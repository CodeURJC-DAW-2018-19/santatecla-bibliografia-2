import { RouterModule } from '@angular/router';

import { BookListComponent } from './book-list.component';
import { BookDetailComponent } from './book-detail.component';
import { BookFormComponent } from './book-form.component';

//ours
import { IndexComponent } from './index.component';
import { ThemeDetailComponent } from './pages/theme/theme-detail.component';
import { ThemeFormComponent } from './pages/theme/theme-form.component';
import { WorkFormComponent } from './pages/literaryWork/work-form.component';
import { WorkDetailComponent } from './pages/literaryWork/work-detail.component';
import { AuthorFormComponent } from './pages/author/author-form.component';
import { AuthorDetailComponent } from './pages/author/author-detail.component';


const appRoutes = [
/*   { path: 'books', component: BookListComponent, useAsDefault: true },
  { path: 'book/new', component: BookFormComponent },
  { path: 'book/:id', component: BookDetailComponent },
  { path: 'book/edit/:id', component: BookFormComponent },
  { path: '', redirectTo: 'books', pathMatch: 'full' }, */

  { path: '', component: IndexComponent, useAsDefault: true },
  { path: 'theme/new', component: ThemeFormComponent },
  { path: 'theme/:id', component: ThemeDetailComponent },
  { path: 'theme/edit/:id', component: ThemeFormComponent },
  { path: 'work/new', component: WorkFormComponent },
  { path: 'work/:id', component: WorkDetailComponent },
  { path: 'work/edit/:id', component: WorkFormComponent },
  { path: 'author/new', component: AuthorFormComponent },
  { path: 'author/:id', component: AuthorDetailComponent },
  { path: 'author/edit/:id', component: AuthorFormComponent },
  { path: '', redirectTo: '', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
