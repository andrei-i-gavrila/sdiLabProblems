import {Component, OnInit} from '@angular/core';
import {StudentsService} from "../shared/students.service";
import {Student} from "../shared/student";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit {

  student: Student;

  constructor(private studentService: StudentsService, private route: ActivatedRoute,private router:Router) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(value => this.studentService.getStudent(value.id)
        .subscribe(student => this.student = student));
  }

  deleteStudent()
  {
    this.studentService.deleteStudent(this.student.id).subscribe(_ => this.router.navigate(['/students']));
  }
}
