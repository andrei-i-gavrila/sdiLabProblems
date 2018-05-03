import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Problem} from "./problem";
import {environment} from "../../../environments/environment";
import {ResponseData} from "../../shared/response";
import {map} from "rxjs/operators";

@Injectable()
export class ProblemsService {

  private problemsUrl = environment.baseUrl + "api/problems";

  constructor(private httpClient: HttpClient) {
  }

  getProblems(): Observable<Problem[]> {
    return this.httpClient.get<ResponseData<Problem[]>>(this.problemsUrl).pipe(
      map((response: ResponseData<Problem[]>) => response.data)
    );
  }

  getProblem(id: number): Observable<Problem> {
    return this.httpClient.get<ResponseData<Problem>>(this.problemsUrl + "/" + id).pipe(
      map(response => response.success ? response.data : null)
    );
  }

  saveProblem(problem : Problem): Observable<Problem> {
    return this.httpClient.post<ResponseData<Problem>>(this.problemsUrl + "/create", problem).pipe(
      map(response => response.success ? response.data : null)
    );
  }

  deleteProblem(id:number) : Observable<any>{
    return this.httpClient.delete(this.problemsUrl+"/delete/" + id).pipe(
      map(response => console.log(response))
    );
  }
}
