import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Assignment} from './assignment';
import {environment} from "../../../environments/environment";
import {ResponseData} from "../../shared/response";
import {map} from "rxjs/operators";

@Injectable()
export class AssignmentsService {

  private assignmentsUrl = environment.baseUrl + "api/assignments";

  constructor(private httpClient: HttpClient) {
  }

  getAssignments(): Observable<Assignment[]> {
    return this.httpClient.get<ResponseData<Assignment[]>>(this.assignmentsUrl).pipe(
      map((response: ResponseData<Assignment[]>) => response.data)
    );
  }

  getAssignment(id: number): Observable<Assignment> {
    return this.httpClient.get<ResponseData<Assignment>>(this.assignmentsUrl + "/" + id).pipe(
      map(response => response.success ? response.data : null)
    );
  }

  createAssignment(student, problem): Observable<Assignment> {
    return this.httpClient.post<ResponseData<Assignment>>(this.assignmentsUrl + "/", {studentId: parseInt(student), problemId: parseInt(problem)}).pipe(
      map(response => response.success ? response.data : null)
    );
  }

  deleteAssignment(id:number) : Observable<any>{
    return this.httpClient.delete(this.assignmentsUrl+"/" + id).pipe(
      map(response => console.log(response))
    );
  }
}
