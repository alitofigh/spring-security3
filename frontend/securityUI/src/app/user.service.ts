import {Injectable, OnDestroy, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, Subscription} from "rxjs";
import {User} from "./user";
import {SharedData} from "./shared-data";

@Injectable({
  providedIn: 'root'
})
export class UserService implements OnInit, OnDestroy {

  token:string = '';
  subscription = Subscription.prototype;
  private readonly userUrl:string;

  constructor(private http: HttpClient, private sharedData:SharedData) {
    this.userUrl='http://localhost:8085/api';
    this.ngOnInit();
  }

  ngOnInit() {
    console.log('onInit method is calling...');
    this.subscription = this.sharedData.currentToken.subscribe(message => this.token = message);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  public findAll():Observable<User[]> {
    var reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.token}`
    });
    return this.http.get<User[]>(this.userUrl + '/user/ls', { headers: reqHeader });
  }

  public save(user: User) {
    var reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.token}`
    });
    return this.http.post<User>(this.userUrl + '/user/add-user', user, { headers: reqHeader });
  }

  public getToken(username: string, password: string):Observable<any> {
    let auth:AuthRequest = new AuthRequest(username, password);
    const reqHeader = new HttpHeaders({
      'Content-Type': 'application/Json'
    });
    return this.http.post(this.userUrl + '/auth/authenticate', auth, {headers: reqHeader});
  }
}
class AuthRequest {
  username: string;
  password: string;
  constructor(username: string, password: string) {
    this.username = username;
    this.password = password;
  }
}
