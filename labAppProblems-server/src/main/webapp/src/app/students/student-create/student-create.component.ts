import {Component, OnInit} from '@angular/core';
import {StudentsService} from "../shared/students.service";
import {Student} from "../shared/student";
import {Router} from "@angular/router";

@Component({
  selector: 'app-student-create',
  templateUrl: './student-create.component.html',
  styleUrls: ['./student-create.component.css']
})
export class StudentCreateComponent implements OnInit {

  name: string = "";
  registrationNumber: number;
  groupNumber: number;

  constructor(private studentService: StudentsService, private router: Router) {
  }

  ngOnInit() {
  }

  createStudent() {
    console.log(this.name)
    console.log(this.registrationNumber)
    console.log(this.groupNumber)

    this.studentService.saveStudent(new Student(this.name, this.registrationNumber, this.groupNumber))
      .subscribe(_ => this.router.navigate(['/students']))
  }
}
