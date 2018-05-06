import {Component, OnInit} from '@angular/core';
import {AssignmentsService} from '../shared/assignment.service';
import {Assignment} from '../shared/assignment';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-assignment-detail',
  templateUrl: './assignment-detail.component.html',
  styleUrls: ['./assignment-detail.component.css']
})
export class AssignmentDetailComponent implements OnInit {

  assignment: Assignment;

  constructor(private assignmentService: AssignmentsService, private route: ActivatedRoute, private  router: Router) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(value => this.assignmentService.getAssignment(value.id)
        .subscribe(assignment => {
          this.assignment = assignment;
          console.log(this.assignment);
        }));
  }

  deleteAssignment()
   {
   this.assignmentService.deleteAssignment(this.assignment.id).subscribe(_ => this.router.navigate(['/assignments']));
   }

}
