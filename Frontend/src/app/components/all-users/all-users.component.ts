import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { Router } from '@angular/router';
import { UserFormComponent } from '../user-form/user-form.component';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {

  private users:User[];

  constructor(private _userService:UserService, private _router:Router) { 
    _router.events.subscribe(event => {
      if (event instanceof UserFormComponent) {
        this.ngOnInit();
      }
    })
  }

  ngOnInit() {
    this._userService.getUsers().subscribe((users) => {
      console.log(users);
      this.users = users;
    }, error => {
      console.log(error);
    })
  }

  editUser(user) {
    this._userService.setter(user);
    this._router.navigate(['/user']);
  }

  createUser() {
    let user = new User();
    this._userService.setter(user);
    this._router.navigate(['/user']);
  }
}
