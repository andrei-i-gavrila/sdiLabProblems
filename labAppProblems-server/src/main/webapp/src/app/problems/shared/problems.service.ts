import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Problem} from "./problem";
import {environment} from "../../../environments/environment";

@Injectable()
export class ProblemsService {

  private problemsUrl = environment.baseUrl + "api/problems";

  constructor(private httpClient: HttpClient) {
  }

  getProblems(): Observable<Problem[]> {
    return this.httpClient.get<Problem[]>(this.problemsUrl);
  }

  getProblem(id: number): Observable<Problem> {
    return this.httpClient.get<Problem>(this.problemsUrl + "/" + id);
  }

  saveProblem(problem : Problem): Observable<Problem> {
    return this.httpClient.post<Problem>(this.problemsUrl + "/", problem);
  }

  updateProblem(problem : Problem): Observable<Problem> {
    return this.httpClient.put<Problem>(this.problemsUrl, problem);
  }

  deleteProblem(id:number) : Observable<any>{
    return this.httpClient.delete(this.problemsUrl + "/" + id);
  }
}
