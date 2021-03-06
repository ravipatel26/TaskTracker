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
                  firstName: ['', [Validators.required, Validators.maxLength(20)]],
                  lastName: ['', [Validators.required, Validators.maxLength(20)]],
                  username: [{value: '', disabled: this.user.username !== undefined}, [Validators.required, Validators.maxLength(20)]],
                  password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]],
                  confirmationPassword: [this.user.password, [Validators.required, Validators.minLength(6), Validators.maxLength(20)]]
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
      console.log(data);
      setTimeout(() => {
        this._router.navigate(["/admin/userList"]);
      }, 2500);
    }, (error) => {
      console.log(error);
      return;
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
      return;
    })
  }
}
