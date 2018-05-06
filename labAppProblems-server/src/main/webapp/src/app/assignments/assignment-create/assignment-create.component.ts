import {Component, OnInit} from '@angular/core';
import {AssignmentsService} from '../shared/assignment.service';
import {Assignment} from '../shared/assignment'
import {Router} from "@angular/router";

@Component({
  selector: 'app-assignment-create',
  templateUrl: './assignment-create.component.html',
  styleUrls: ['./assignment-create.component.css']
})
export class AssignmentCreateComponent implements OnInit {

  title: string = "";
  registrationNumber: number;

  constructor(private  assignmentService: AssignmentsService, private  router: Router){}

  ngOnInit() {
  }

  createAssignment() {
    console.log(this.registrationNumber)
    console.log(this.title)

    this.assignmentService.saveAssignment(new Assignment(this.title,this.registrationNumber))
      .subscribe(_ => this.router.navigate(['/assignment']))
  }
}
