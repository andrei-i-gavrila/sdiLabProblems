import {Component, OnInit} from '@angular/core';
import {StudentsService} from "../shared/students.service";
import {Student} from "../shared/student";

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
})
export class StudentsListComponent implements OnInit {

  students: Array<Student>;
  nameFilter: string;

  constructor(private studentService: StudentsService) {
  }

  getStudents() {
    console.log(this.nameFilter);
    this.studentService.getStudents(this.nameFilter)
      .subscribe(
        students => this.students = students,
        error => console.log(error)
      );
  }

  ngOnInit() {
    this.getStudents()
  }

}
