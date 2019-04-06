import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { JsonpModule } from '@angular/http';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatIconRegistry } from '@angular/material/icon';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
//Our Components 
/* import { IndexComponent } from './pages/index/index.component';
import { AuthorNewComponent } from './pages/author/author-new.component';
import { AuthorComponent } from './pages/author/author.component';
import { ThemeComponent } from './pages/theme/theme.component';
import { ThemeNewComponent } from './pages/theme/theme-new.component';
import { WorkComponent } from './pages/literaryWork/work.component';
import { WorkShowComponent } from './pages/literaryWork/work-show.component'; */
//

import {
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatCardModule,
    MatMenuModule,
    MatInputModule,
    MatButtonToggleModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatDialogModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatTabsModule,
    MatSidenavModule,
    MatTooltipModule,
    MatRippleModule,
    MatRadioModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSliderModule,
    MatAutocompleteModule,
} from '@angular/material';

import {
    CovalentCommonModule,
    CovalentLayoutModule,
    CovalentMediaModule,
    CovalentExpansionPanelModule,
    CovalentStepsModule,
    CovalentLoadingModule,
    CovalentDialogsModule,
    CovalentSearchModule,
    CovalentPagingModule,
    CovalentNotificationsModule,
    CovalentMenuModule,
    CovalentDataTableModule,
    CovalentMessageModule,
} from '@covalent/core';

import { NgxChartsModule } from '@swimlane/ngx-charts';
import { DomSanitizer } from '@angular/platform-browser';
import { BookService } from './book.service';
import { LoginService } from './auth/login.service';
import { BookDetailComponent } from './book-detail.component';
import { BookListComponent } from './book-list.component';
import { BookFormComponent } from './book-form.component';
import { LoginComponent } from './login.component';
import { routing } from './app.routing';
import { ErrorInterceptor } from './auth/error.interceptor';
import { BasicAuthInterceptor } from './auth/auth.interceptor';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { ThemeService } from './pages/theme/theme.service';
import { ThemeDetailComponent } from './pages/theme/theme-detail.component';
import { ThemeListComponent } from './pages/theme/theme-list.component';
import { ThemeFormComponent } from './pages/theme/theme-form.component';

import { AuthorService } from './pages/author/author.service';
import { AuthorDetailComponent } from './pages/author/author-detail.component';
import { AuthorListComponent } from './pages/author/author-list.component';
import { AuthorFormComponent } from './pages/author/author-form.component';

import { WorkService } from './pages/literaryWork/work.service';
import { WorkComponent } from './pages/literaryWork/work.component';
import { WorkShowComponent } from './pages/literaryWork/work-show.component'; 
//Our Services 
/*import { WorkService } from './pages/literaryWork/work.service';
import { AuthorService } from './pages/author/author.service';
import { ThemeService } from './pages/theme/theme.service'; */
//
@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        RouterModule.forRoot([]),
        HttpClientModule,
        JsonpModule,
        /** Material Modules */
        MatButtonModule,
        MatListModule,
        MatIconModule,
        MatCardModule,
        MatMenuModule,
        MatInputModule,
        MatSelectModule,
        MatButtonToggleModule,
        MatSlideToggleModule,
        MatProgressSpinnerModule,
        MatDialogModule,
        MatSnackBarModule,
        MatToolbarModule,
        MatTabsModule,
        MatSidenavModule,
        MatTooltipModule,
        MatRippleModule,
        MatRadioModule,
        MatGridListModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatSliderModule,
        MatAutocompleteModule,
        MatInputModule,
        /** Covalent Modules */
        CovalentCommonModule,
        CovalentLayoutModule,
        CovalentMediaModule,
        CovalentExpansionPanelModule,
        CovalentStepsModule,
        CovalentDialogsModule,
        CovalentLoadingModule,
        CovalentSearchModule,
        CovalentPagingModule,
        CovalentNotificationsModule,
        CovalentMenuModule,
        CovalentDataTableModule,
        CovalentMessageModule,
        /** Additional **/
        NgxChartsModule,
        routing,
    ],
    declarations: [AppComponent, BookDetailComponent, BookListComponent, BookFormComponent, LoginComponent, ThemeListComponent, ThemeDetailComponent, ThemeFormComponent, AuthorDetailComponent, AuthorListComponent, AuthorFormComponent, WorkShowComponent, WorkComponent , /* WorkComponent,IndexComponent, AuthorNewComponent, AuthorComponent, ThemeComponent, ThemeNewComponent, WorkComponent, WorkShowComponent */],
    //declarations: [AppComponent, BookDetailComponent, BookListComponent, BookFormComponent, LoginComponent],
    bootstrap: [AppComponent],
    providers: [BookService, LoginService, ThemeService, AuthorService,WorkService,/*  WorkService, AuthorService, ThemeService, */
    //providers: [BookService, LoginService, 
        { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        { provide: LocationStrategy, useClass: HashLocationStrategy }
    ],
})
export class AppModule {
    constructor(private matIconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer) {
        matIconRegistry.addSvgIconSet(domSanitizer.bypassSecurityTrustResourceUrl('/assets/symbol-defs.svg'));
    }
}
