import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AllUsersComponent } from './components/all-users/all-users.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { UserService } from './services/user.service';

const appRoutes:Routes = [
  { path:'', component:AllUsersComponent },
  { path:'addUser', component:UserFormComponent },
]

@NgModule({
  declarations: [
    AppComponent,
    AllUsersComponent,
    UserFormComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }