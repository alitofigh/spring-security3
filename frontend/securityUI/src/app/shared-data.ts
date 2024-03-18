import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class SharedData {
  private token = new BehaviorSubject('eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX0FETUlOIl0sInN1YiI6ImFsaUBnbWFpbC5jb20iLCJpYXQiOjE3MTA3MzkwMTQsImV4cCI6MTcxMDc0OTgxNH0.r1OED08f8GSaUsqKJI0mBY_qt5nCay8bCNybe4IO6a4');
  currentToken = this.token.asObservable();

  constructor() { }

  changeMessage(message: string) {
    this.token.next(message)
  }
}
