import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {StudentsComponent} from "./students/students.component";
import {StudentCreateComponent} from './students/student-create/student-create.component';
import {StudentDetailComponent} from './students/student-detail/student-detail.component';
import {ProblemsComponent} from "./problems/problems.component";
import {ProblemDetailComponent} from './problems/problem-detail/problem-detail.component';
import {ProblemCreateComponent} from './problems/problem-create/problem-create.component';


const routes: Routes = [
  {path: 'students', component: StudentsComponent},
  {path: 'students/create', component: StudentCreateComponent},
  {path: 'students/:id', component: StudentDetailComponent},
  {path: 'problems', component: ProblemsComponent},
  {path: 'problems/create', component:ProblemCreateComponent},
  {path: 'problems/:id',component: ProblemDetailComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
