import { RouterModule } from '@angular/router';

import { BookListComponent } from './book-list.component';
import { BookDetailComponent } from './book-detail.component';
import { BookFormComponent } from './book-form.component';

//ours
import { ThemeListComponent } from './pages/theme/theme-list.component';
import { ThemeDetailComponent } from './pages/theme/theme-detail.component';
import { ThemeFormComponent } from './pages/theme/theme-form.component';
import { WorkFormComponent } from './pages/literaryWork/work-form.component';


const appRoutes = [
/*   { path: 'books', component: BookListComponent, useAsDefault: true },
  { path: 'book/new', component: BookFormComponent },
  { path: 'book/:id', component: BookDetailComponent },
  { path: 'book/edit/:id', component: BookFormComponent },
  { path: '', redirectTo: 'books', pathMatch: 'full' }, */

  { path: 'themes', component: ThemeListComponent, useAsDefault: true },
  { path: 'theme/new', component: ThemeFormComponent },
  { path: 'theme/:id', component: ThemeDetailComponent },
  { path: 'theme/edit/:id', component: ThemeFormComponent },
  { path: 'work/new', component: WorkFormComponent },
  { path: '', redirectTo: 'themes', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
