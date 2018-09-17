import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  private user:User;

  constructor(private _userService:UserService, private _router:Router) { }

  ngOnInit() {
    this.user=this._userService.getter();
  }

  submitUser() {
    if (this.user.id == undefined) {
      this._userService.createUser(this.user).subscribe((data)=>{
        this._router.navigate(["/"]);
        console.log(data);
      },(error)=>{
        console.log(error);
      })
    } else {
      this._userService.editUser(this.user, this.user.id).subscribe((data)=>{
        this._router.navigate(["/"]);
        console.log(data);
      },(error)=>{
        console.log(error);
      })
    }
  }

  deleteUser(user) {
    console.log(user);
    this._userService.deleteUser(user.id).subscribe((data) => {
      console.log(data);
      this._router.navigate(["/"]);
    }, (error) => {
      console.log(error);
    });
  }
}
