import {Component} from '@angular/core';
import {StudentsService} from "../shared/students.service";
import {Student} from "../shared/student";
import {Router} from "@angular/router";

@Component({
  selector: 'app-student-create',
  templateUrl: './student-create.component.html',
})
export class StudentCreateComponent {

  name: string;
  registrationNumber: number;
  groupNumber: number;

  constructor(private studentService: StudentsService, private router: Router) {
  }

  createStudent() {
    this.studentService.saveStudent(new Student(this.name, this.registrationNumber, this.groupNumber))
      .subscribe(
        () => this.router.navigate(['/students']),
        () => {
          this.name = null;
          this.registrationNumber = null
          this.groupNumber = null;
        }
      )
  }
}
