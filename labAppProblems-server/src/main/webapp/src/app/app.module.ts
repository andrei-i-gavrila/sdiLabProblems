import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app.routing";
import {HomeComponent} from './home/home.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";

import {StudentsComponent} from './students/students.component';
import {StudentsListComponent} from './students/students-list/students-list.component';
import {StudentDetailComponent} from './students/student-detail/student-detail.component';
import {StudentCreateComponent} from './students/student-create/student-create.component';
import {StudentsService} from "./students/shared/students.service";

import {ProblemsComponent} from './problems/problems.component';
import {ProblemsListComponent} from "./problems/problems-list/problems-list.component";
import {ProblemDetailComponent} from './problems/problem-detail/problem-detail.component';
import {ProblemCreateComponent} from './problems/problem-create/problem-create.component';
import {ProblemsService} from "./problems/shared/problems.service";

import {AssignmentsComponent} from './assignments/assignments.component';
import {AssignmentsListComponent} from './assignments/assignments-list/assignments-list.component';
import {AssignmentsService} from './assignments/shared/assignment.service';
import {AssignmentDetailComponent} from './assignments/assignment-detail/assignment-detail.component';
import {AssignmentCreateComponent} from './assignments/assignment-create/assignment-create.component';
import {ErrorInterceptor} from "./error.interceptor";
import {ToasterModule} from "angular2-toaster";
import {ErrorService} from "./error.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";


@NgModule({
  declarations: [
    AppComponent,
    StudentsComponent,
    StudentsListComponent,
    StudentDetailComponent,
    StudentCreateComponent,
    ProblemsComponent,
    ProblemsListComponent,
    ProblemDetailComponent,
    ProblemCreateComponent,
    AssignmentsComponent,
    AssignmentsListComponent,
    AssignmentDetailComponent,
    AssignmentCreateComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ToasterModule.forRoot()
  ],
  providers: [
    StudentsService,ProblemsService,AssignmentsService, HttpClientModule, ErrorService,
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
