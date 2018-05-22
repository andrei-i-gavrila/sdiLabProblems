import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {Subject} from "rxjs/Subject";
import {Error} from "./shared/response"

@Injectable()
export class ErrorService {

  private errors: Subject<string> = new Subject<string>();


  public getErrors(): Observable<string> {
    return this.errors.asObservable()
  }

  public pushError(error: string) {
    this.errors.next(error)
  }

  public pushErrors(errors: Error[]): void {
    errors.forEach(error => this.errors.next(error.message))
  }
}
