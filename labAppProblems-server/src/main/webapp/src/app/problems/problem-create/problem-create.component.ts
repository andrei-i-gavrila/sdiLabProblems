import {Component, OnInit} from '@angular/core';
import {ProblemsService} from "../shared/problems.service"
import {Problem} from "../shared/problem"
import {Router} from "@angular/router";

@Component({
  selector: 'app-problem-create',
  templateUrl: './problem-create.component.html',
  styleUrls: ['./problem-create.component.css']
})
export class ProblemCreateComponent implements OnInit {

  title: string = "";
  description: string = "";

  constructor(private  problemService: ProblemsService, private  router: Router){}

  ngOnInit() {
  }

  createProblem() {
    console.log(this.title)
    console.log(this.description)

    this.problemService.saveProblem(new Problem(this.title,this.description))
      .subscribe(_ => this.router.navigate(['/problems']))
  }
}
