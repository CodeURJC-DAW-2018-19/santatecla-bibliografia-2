import { RouterModule } from '@angular/router';

import { BookListComponent } from './book-list.component';
import { BookDetailComponent } from './book-detail.component';
import { BookFormComponent } from './book-form.component';
import {AuthorNewComponent} from "./pages/author/author-new.component";
import {IndexComponent} from "./pages/index/index.component";
import {AuthorComponent} from "./pages/author/author.component";

const appRoutes = [
  { path: 'books', component: BookListComponent, useAsDefault: true },
  { path: 'book/new', component: BookFormComponent },
  { path: 'book/:id', component: BookDetailComponent },
  { path: 'book/edit/:id', component: BookFormComponent },
  { path: '', redirectTo: 'books', pathMatch: 'full' },
  { path: 'author/new', component: AuthorNewComponent },
  { path: 'author/:name', component: AuthorComponent },
  { path: '/', component: IndexComponent }
];

export const routing = RouterModule.forRoot(appRoutes);
