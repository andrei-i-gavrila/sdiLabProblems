import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {StudentsComponent} from "./students/students.component";
import {StudentCreateComponent} from './students/student-create/student-create.component';

import {ProblemsComponent} from "./problems/problems.component";
import {ProblemDetailComponent} from './problems/problem-detail/problem-detail.component';
import {ProblemCreateComponent} from './problems/problem-create/problem-create.component';

import {AssignmentsComponent} from './assignments/assignments.component';
import {AssignmentCreateComponent} from "./assignments/assignment-create/assignment-create.component";


const routes: Routes = [
  {path: 'students', component: StudentsComponent},
  {path: 'students/create', component: StudentCreateComponent},
  {path: 'problems', component: ProblemsComponent},
  {path: 'problems/create', component:ProblemCreateComponent},
  {path: 'problems/:id',component: ProblemDetailComponent},
  {path: 'assignments', component: AssignmentsComponent},
  {path: 'assignments/create', component: AssignmentCreateComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
