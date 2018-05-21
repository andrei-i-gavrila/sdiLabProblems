import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ProblemsService} from "../shared/problems.service";
import {Problem} from "../shared/problem";

@Component({
  selector: '[app-problem-detail]',
  templateUrl: './problem-detail.component.html',
})
export class ProblemDetailComponent {

  @Input() problem: Problem;
  readonly: boolean = true;
  @Output() onTriggerRefresh = new EventEmitter();

  constructor(private problemService: ProblemsService) {
  }

  deleteProblem()
  {
    this.problemService
      .deleteProblem(this.problem.id)
      .subscribe(() => this.onTriggerRefresh.emit());
  }

  updateProblem() {
    this.problemService
      .updateProblem(this.problem)
      .subscribe(() => this.finishEdit())

  }

  startEdit() {
    this.readonly = false;
  }

  finishEdit() {
    this.readonly = true;
    this.problemService
      .getProblem(this.problem.id)
      .subscribe(response => this.problem = response)
  }

}
