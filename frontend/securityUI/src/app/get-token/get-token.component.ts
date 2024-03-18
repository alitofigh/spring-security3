import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";
import {SharedData} from "../shared-data";

@Component({
  selector: 'app-get-token',
  templateUrl: './get-token.component.html',
  styleUrls: ['./get-token.component.sass']
})
export class GetTokenComponent {

  username: string = '';
  password: string = '';

  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UserService,
              private sharedData: SharedData) {
  }

  onSubmit() {
    this.userService.getToken(this.username, this.password).subscribe(result => {
      this.sharedData.changeMessage(result['token']);
      this.gotoUserList();
    });
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }
}
