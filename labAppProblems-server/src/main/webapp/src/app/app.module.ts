import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app.routing";
import {StudentsComponent} from './students/students.component';
import {StudentsListComponent} from './students/students-list/students-list.component';
import {StudentDetailComponent} from './students/student-detail/student-detail.component';
import {StudentCreateComponent} from './students/student-create/student-create.component';
import {HomeComponent} from './home/home.component';
import {StudentsService} from "./students/shared/students.service";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {ProblemsComponent} from "./problems/problems.component";
import {ProblemsListComponent} from "./problems/problems-list/problems-list.component";
import {ProblemsService} from "./problems/shared/problems.service";


@NgModule({
  declarations: [
    AppComponent,
    StudentsComponent,
    StudentsListComponent,
    StudentDetailComponent,
    StudentCreateComponent,
    ProblemsComponent,
    ProblemsListComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [StudentsService,ProblemsService, HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
