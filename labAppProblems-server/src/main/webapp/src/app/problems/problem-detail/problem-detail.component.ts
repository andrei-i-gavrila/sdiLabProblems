import {Component, OnInit} from '@angular/core';
import {ProblemsService} from "../shared/problems.service";
import {Problem} from "../shared/problem";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-problem-detail',
  templateUrl: './problem-detail.component.html',
  styleUrls: ['./problem-detail.component.css']
})
export class ProblemDetailComponent implements OnInit {

  problem: Problem;

  constructor(private problemService: ProblemsService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(value => this.problemService.getProblem(value.id)
        .subscribe(problem => this.problem = problem));
  }

}
