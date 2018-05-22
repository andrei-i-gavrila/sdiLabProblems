import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {map, tap} from "rxjs/operators";
import {ErrorService} from "./error.service";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {


  constructor(private errorService: ErrorService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      tap(event => {
          if ((event instanceof HttpResponse) && event.body.success == false) {
            this.errorService.pushErrors(event.body.errors);
            throw new Error()
          }
        },
        error => this.errorService.pushError(error.message)
      ),
      map(event => (event instanceof HttpResponse) ? event.clone({body: event.body.data}) : event)
    )
  }

}
