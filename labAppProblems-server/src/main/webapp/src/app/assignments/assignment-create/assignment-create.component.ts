import {Component, OnInit} from '@angular/core';
import {AssignmentsService} from '../shared/assignment.service';
import {Router} from "@angular/router";
import {Student} from "../../students/shared/student";
import {Problem} from "../../problems/shared/problem";
import {StudentsService} from "../../students/shared/students.service";
import {ProblemsService} from "../../problems/shared/problems.service";

@Component({
  selector: 'app-assignment-create',
  templateUrl: './assignment-create.component.html'
})
export class AssignmentCreateComponent implements OnInit {

  student: Student = null;
  problem: Problem = null;

  students: Array<Student>;
  problems: Array<Problem>;

  constructor(private  assignmentService: AssignmentsService, private studentService: StudentsService, private problemService: ProblemsService, private  router: Router) {
  }

  ngOnInit() {
    this.studentService.getStudents().subscribe(value => this.students = value);
    this.problemService.getProblems().subscribe(value => this.problems = value);
  }

  createAssignment() {
    if (this.problem == null || this.student == null) {
      return
    }
    this.assignmentService.createAssignment(this.student, this.problem)
      .subscribe(
        () => this.router.navigate(['/assignments']),
        () => {
          this.student = null;
          this.problem = null;
        }
      )
  }
}
