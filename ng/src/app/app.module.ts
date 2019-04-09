import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { JsonpModule } from '@angular/http';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatIconRegistry } from '@angular/material/icon';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { DynamicTabsDirective } from './pages/tabs/dynamic-tabs.directive';
import { TabsComponent } from './pages/tabs/tabs.component';
import { TabComponent } from './pages/tabs/tab.component';

import {ScrollDispatchModule} from '@angular/cdk/scrolling';

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
import {A11yModule} from '@angular/cdk/a11y';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {PortalModule} from '@angular/cdk/portal';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {CdkStepperModule} from '@angular/cdk/stepper';
import {CdkTableModule} from '@angular/cdk/table';
import {CdkTreeModule} from '@angular/cdk/tree';
import {
  MatBadgeModule,
  MatBottomSheetModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDividerModule,
  MatExpansionModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTreeModule,
} from '@angular/material';
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
import { LoginService } from './auth/login.service';
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
import { WorkDetailComponent } from './pages/literaryWork/work-detail.component';
import { WorkListComponent } from './pages/literaryWork/work-list.component'; 
import { WorkFormComponent } from './pages/literaryWork/work-form.component';

import { IndexComponent } from './index.component';


import { ChartComponent} from './pages/chart/chart.component';

import { CovalentBaseEchartsModule } from '@covalent/echarts/base';
import { CovalentBarEchartsModule } from '@covalent/echarts/bar';
import { CovalentTooltipEchartsModule } from '@covalent/echarts/tooltip';


import { TabService } from './tabs/tab.service';

//Our Services 
/*import { WorkService } from './pages/literaryWork/work.service';
import { AuthorService } from './pages/author/author.service';
import { ThemeService } from './pages/theme/theme.service'; */
//
@NgModule({
    imports: [
        CovalentBaseEchartsModule,
        CovalentBarEchartsModule,
        CovalentTooltipEchartsModule,
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        RouterModule.forRoot([]),
        HttpClientModule,
        JsonpModule,
        ScrollDispatchModule,
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
        MatPaginatorModule,
        MatExpansionModule,
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
    declarations: [AppComponent, LoginComponent,
          ThemeListComponent, ThemeDetailComponent, ThemeFormComponent,
           AuthorDetailComponent, AuthorListComponent, AuthorFormComponent,
            WorkDetailComponent , WorkListComponent, WorkFormComponent,IndexComponent,
            TabsComponent, TabComponent, DynamicTabsDirective, ChartComponent/* WorkComponent,IndexComponent, AuthorNewComponent, AuthorComponent, ThemeComponent, ThemeNewComponent, WorkComponent, WorkShowComponent */],

    //declarations: [AppComponent, BookDetailComponent, BookListComponent, BookFormComponent, LoginComponent],
    bootstrap: [AppComponent],


    providers: [ LoginService, ThemeService, AuthorService, WorkService, TabService,
        { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        { provide: LocationStrategy, useClass: HashLocationStrategy }
    ],
    entryComponents: [TabComponent],

    exports: [
    A11yModule,
    CdkStepperModule,
    CdkTableModule,
    CdkTreeModule,
    DragDropModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,
    PortalModule,
    ScrollingModule,
  ]
})
export class AppModule {
constructor(private matIconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer) {
    matIconRegistry.addSvgIconSet(domSanitizer.bypassSecurityTrustResourceUrl('/assets/symbol-defs.svg'));
}
}
