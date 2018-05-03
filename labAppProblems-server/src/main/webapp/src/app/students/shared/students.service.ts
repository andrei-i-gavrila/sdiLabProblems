import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Student} from "./student";
import {environment} from "../../../environments/environment";
import {ResponseData} from "../../shared/response";
import {map} from "rxjs/operators";

@Injectable()
export class StudentsService {

  private studentsUrl = environment.baseUrl + "api/students";

  constructor(private httpClient: HttpClient) {
  }

  getStudents(): Observable<Student[]> {
    return this.httpClient.get<ResponseData<Student[]>>(this.studentsUrl).pipe(
      map((response: ResponseData<Student[]>) => response.data)
    );
  }

  getStudent(id: number): Observable<Student> {
    return this.httpClient.get<ResponseData<Student>>(this.studentsUrl + "/" + id).pipe(
      map(response => response.success ? response.data : null)
    );
  }

  saveStudent(student: Student): Observable<Student> {
    return this.httpClient.post<ResponseData<Student>>(this.studentsUrl + "/create", student).pipe(
      map(response => response.success ? response.data : null)
    );
  }

  deleteStudent(id:number) : Observable<any>{
    return this.httpClient.delete(this.studentsUrl+"/delete/" + id).pipe(
      map(response => console.log(response))
    );
  }

}
