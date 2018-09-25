import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../services/authentication.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private loginForm: FormGroup;
  private submitted = false;
  private loading = false;

  constructor(private _userService:UserService, private _router:Router, private route: ActivatedRoute,
              private formBuilder: FormBuilder, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    // reset login status
    this.authenticationService.logout();
  }

  get form() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.loginForm.controls.username.value, this.loginForm.controls.password.value).subscribe((user:User)=>{
      if (user !== null) {
        if (user.role === "admin")
          this._router.navigate(["/admin/userList"]);
        else
          this._router.navigate(["/user/taskList"]);
        localStorage.setItem('currentUser', JSON.stringify({ id : user.id, role : user.role }));
      }        
      else {
        alert('Invalid username/password');
      }
      console.log(user);
    },(error)=>{
      alert('Could not validate username/password');
      this.loading = false;
      console.log(error);
    });
  }
}
