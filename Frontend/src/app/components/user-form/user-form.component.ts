import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  private user:User;
  private registerForm: FormGroup;
  private submitted = false;

  constructor(private _userService:UserService, private _router:Router, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.user = this._userService.getter();
    this.registerForm = this.formBuilder.group({
                  firstName: ['', Validators.required],
                  lastName: ['', Validators.required],
                  username: [{value: '', disabled: this.user.username !== undefined}, Validators.required],
                  password: ['', [Validators.required, Validators.minLength(6)]],
                  confirmationPassword: [this.user.password, [Validators.required, Validators.minLength(6)]]
                });
  }

  get form() {
    return this.registerForm.controls;
  }

  submitUser() {
    this.submitted = true;
 
    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    if (this.registerForm.controls.confirmationPassword.value !== this.registerForm.controls.password.value) {
      alert("Passwords don't match!\n");
      return;
    }

    if (this.user.id === undefined) {
      this.createUser();
    } else {
      this.editUser();
    }
  }

  deleteUser(user) {
    this._userService.deleteUser(user.id).subscribe((data) => {
      this._router.navigate(["/admin/userList"]);
    }, (error) => {
      console.log(error);
    });
  }

  findInvalidControls() {
    const invalid = [];
    const controls = this.registerForm.controls;
    for (const name in controls) {
      if (controls[name].invalid) {
        invalid.push(name);
      }
    }
    return invalid;
  }

  createUser() {
    this._userService.isUniqueUsername(this.registerForm.controls.username.value).subscribe((response)=>{
      if (response) {
        this._userService.createUser(this.user).subscribe((data)=>{
          this._router.navigate(["/admin/userList"]);
        },(error)=>{
          console.log(error);
        })
      } else {
        alert("Username is not unique!\nPlease provide a valid username");
        return;
      }
    },(error)=>{
      console.log(error);
      return;
    })
  }

  editUser() {
    this._userService.editUser(this.user, this.user.id).subscribe((data)=>{
      this._router.navigate(["/admin/userList"]);
    },(error)=>{
      console.log(error);
    })
  }
}
