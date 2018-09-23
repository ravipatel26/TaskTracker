import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { AllUsersComponent } from './components/all-users/all-users.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { UserService } from './services/user.service';
import { AuthenticationService } from './services/authentication.service';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './_guards/auth.guard';
import { TaskListComponent } from './components/task-list/task-list.component';

const appRoutes:Routes = [
  { path:'login', component:LoginComponent },
  { path:'admin/userList', component:AllUsersComponent, canActivate: [AuthGuard] },
  { path:'admin/userForm', component:UserFormComponent, canActivate: [AuthGuard] },
  { path:'user/tasks', component:TaskListComponent, canActivate: [AuthGuard] },
  { path:'admin', redirectTo:'admin/userList' },
  { path:'user', redirectTo:'user/tasks' },
  { path:'**', redirectTo:'login' }
]

@NgModule({
  declarations: [
    AppComponent,
    AllUsersComponent,
    UserFormComponent,
    LoginComponent,
    TaskListComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [AuthGuard, UserService, AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
