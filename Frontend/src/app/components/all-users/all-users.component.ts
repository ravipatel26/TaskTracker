import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { Router, NavigationEnd } from '@angular/router';
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
      console.log(event);
      if (event instanceof UserFormComponent || event instanceof NavigationEnd) {
        setInterval(() => 
          this.ngOnInit()
          , 2000);
      }
    })
  }

  ngOnInit() {
    this._userService.getUsers().subscribe((users) => {
      this.users = users;
    }, error => {
      console.log(error);
    });
  }

  editUser(user) {
    this._userService.setter(user);
    this._router.navigate(['/admin/userForm']);
  }

  createUser() {
    let user = new User();
    this._userService.setter(user);
    this._router.navigate(['/admin/userForm']);
  }
}
