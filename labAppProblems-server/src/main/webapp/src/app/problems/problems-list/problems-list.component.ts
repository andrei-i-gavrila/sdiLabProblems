import {Component, OnInit} from '@angular/core';
import {ProblemsService} from "../shared/problems.service";
import {Problem} from "../shared/problem";

@Component({
  selector: 'app-problems-list',
  templateUrl: './problems-list.component.html',
})
export class ProblemsListComponent implements OnInit {

  problems: Array<Problem>;

  constructor(private problemService: ProblemsService) {
  }

  getProblems() {
    this.problemService.getProblems()
      .subscribe(
        problems => this.problems=problems,
      );
  }

  ngOnInit() {
    this.getProblems();
  }

}
