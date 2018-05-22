import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Assignment} from './assignment';
import {environment} from "../../../environments/environment";
import {Student} from "../../students/shared/student";
import {Problem} from "../../problems/shared/problem";

@Injectable()
export class AssignmentsService {

  private assignmentsUrl = environment.baseUrl + "api/assignments";

  constructor(private httpClient: HttpClient) {
  }

  getAssignments(): Observable<Assignment[]> {
    return this.httpClient.get<Assignment[]>(this.assignmentsUrl)
  }

  getAssignment(id: number): Observable<Assignment> {
    return this.httpClient.get<Assignment>(this.assignmentsUrl + "/" + id);
  }

  createAssignment(student: Student, problem: Problem): Observable<Assignment> {
    return this.httpClient.post<Assignment>(this.assignmentsUrl, {
      studentId: student.id,
      problemId: problem.id
    });
  }

  deleteAssignment(id:number) : Observable<any>{
    return this.httpClient.delete(this.assignmentsUrl + "/" + id)
  }

  gradeAssignment(assignment: Assignment): Observable<Assignment> {
    return this.httpClient.put<Assignment>(this.assignmentsUrl, assignment);
  }
}
