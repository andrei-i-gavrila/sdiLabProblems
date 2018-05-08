import {Component, OnInit} from '@angular/core';
import {AssignmentsService} from '../shared/assignment.service';
import {Assignment} from '../shared/assignment'

@Component({
  selector: 'app-assignments-list',
  templateUrl: './assignments-list.component.html',
  styleUrls: ['./assignments-list.component.css']
})
export class AssignmentsListComponent implements OnInit {

  assignments: Array<Assignment>;

  constructor(private assignmentService: AssignmentsService) {
  }

  getAssignments() {
    this.assignmentService.getAssignments()
      .subscribe(
        assignments => {
          this.assignments = assignments
          console.log(assignments)
        },
        error => console.log(error)
      );
  }

  ngOnInit() {
    this.getAssignments();
  }

}
