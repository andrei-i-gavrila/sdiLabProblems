import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {StudentsComponent} from "./students/students.component";
import {StudentCreateComponent} from "./students/student-create/student-create.component";
import {StudentDetailComponent} from "./students/student-detail/student-detail.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'students', component: StudentsComponent},
  {path: 'students', component: StudentsComponent},
  {path: 'students/create', component: StudentCreateComponent},
  {path: 'students/:id', component: StudentDetailComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
