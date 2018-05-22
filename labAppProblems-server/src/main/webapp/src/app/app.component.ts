import {Component, OnDestroy, OnInit} from '@angular/core';
import {ErrorService} from "./error.service";
import {Subscription} from "rxjs/Subscription";
import {ToasterService} from "angular2-toaster";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'app';
  private errorSubscription: Subscription;

  constructor(private errorService: ErrorService, private toasterService: ToasterService) {
  }

  ngOnDestroy(): void {
    this.errorSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.errorSubscription = this.errorService.getErrors()
      .subscribe(error => this.toasterService.pop('error', 'An error occured', error));
  }

}
