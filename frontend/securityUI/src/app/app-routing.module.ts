import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserListComponent} from "./user-list/user-list.component";
import {UserFormComponent} from "./user-form/user-form.component";
import {GetTokenComponent} from "./get-token/get-token.component";

const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'adduser', component: UserFormComponent },
  { path: 'getToken', component: GetTokenComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
