import {Component} from '@angular/core';
import {ProblemsService} from "../shared/problems.service"
import {Problem} from "../shared/problem"
import {Router} from "@angular/router";

@Component({
  selector: 'app-problem-create',
  templateUrl: './problem-create.component.html',
})
export class ProblemCreateComponent {

  title: string;
  description: string;

  constructor(private  problemService: ProblemsService, private  router: Router){}

  createProblem() {
    this.problemService.saveProblem(new Problem(this.title,this.description))
      .subscribe(_ => this.router.navigate(['/problems']))
  }
}
