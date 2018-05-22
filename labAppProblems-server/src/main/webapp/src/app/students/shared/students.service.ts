import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Student} from "./student";
import {environment} from "../../../environments/environment";

@Injectable()
export class StudentsService {

  private studentsUrl = environment.baseUrl + "api/students";

  constructor(private httpClient: HttpClient) {
  }

  getStudents(nameFilter = ""): Observable<Student[]> {
    return this.httpClient.get<Student[]>(this.studentsUrl + (nameFilter !== "" ? ("?nameFilter=" + nameFilter) : ""))
  }

  getStudent(id: number): Observable<Student> {
    return this.httpClient.get<Student>(this.studentsUrl + "/" + id)
  }

  saveStudent(student: Student): Observable<Student> {
    return this.httpClient.post<Student>(this.studentsUrl + "/", student)
  }

  updateStudent(student: Student): Observable<Student> {
    return this.httpClient.put<Student>(this.studentsUrl, student)
  }

  deleteStudent(id:number) : Observable<any>{
    return this.httpClient.delete(this.studentsUrl + "/" + id)
  }

}
