import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  title = 'securityUI';

  constructor() {
    this.title = 'Spring Boot - Angular Application';
  }
}
