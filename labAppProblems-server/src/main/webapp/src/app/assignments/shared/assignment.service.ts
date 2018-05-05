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
}
