import {Component, EventEmitter, Input, Output} from '@angular/core';
import {AssignmentsService} from '../shared/assignment.service';
import {Assignment} from '../shared/assignment';

@Component({
  selector: '[app-assignment-detail]',
  templateUrl: './assignment-detail.component.html',
})
export class AssignmentDetailComponent {

  @Input() assignment: Assignment;
  readonly: boolean = true;

  @Output() onTriggerRefresh = new EventEmitter();

  constructor(private assignmentService: AssignmentsService) {
  }

  deleteAssignment() {
    this.assignmentService
      .deleteAssignment(this.assignment.id)
      .subscribe(() => this.onTriggerRefresh.emit());
  }

  gradeAssignment() {
    this.assignmentService
      .gradeAssignment(this.assignment)
      .subscribe(() => this.finishEdit());
  }

  startEdit() {
    this.readonly = false;
  }

  finishEdit() {
    this.readonly = true;
    this.assignmentService
      .getAssignment(this.assignment.id)
      .subscribe(response => this.assignment = response)
  }
}
