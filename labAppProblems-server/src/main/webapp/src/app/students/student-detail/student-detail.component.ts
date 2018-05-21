import {Component, EventEmitter, Input, Output} from '@angular/core';
import {StudentsService} from "../shared/students.service";
import {Student} from "../shared/student";

@Component({
  selector: '[app-student-detail]',
  templateUrl: './student-detail.component.html',
})
export class StudentDetailComponent {

  @Input() student: Student;
  readonly: boolean = true;

  @Output() onTriggerRefresh = new EventEmitter();

  constructor(private studentService: StudentsService) {
  }

  deleteStudent()
  {
    this.studentService
      .deleteStudent(this.student.id)
      .subscribe(() => this.onTriggerRefresh.emit());
  }

  startEdit() {
    this.readonly = false;
  }

  finishEdit() {
    this.readonly = true;
    this.studentService
      .getStudent(this.student.id)
      .subscribe(response => this.student = response);
  }

  update() {
    this.studentService
      .updateStudent(this.student)
      .subscribe(() => this.finishEdit());
  }
}
