import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.css']
})
export class AllUsersComponent implements OnInit {

  private users:User[];

  constructor(private _userService:UserService) { }

  ngOnInit() {
    // TODO: use this to get all users!!!
    // may need to do cross origin in java (see part 4: https://www.youtube.com/watch?v=v4_QR4PAPsM)
    // this._userService.getUsers().subscribe((users) => {
    //   console.log(users);
    //   this.users = users;
    // }, error => {
    //   console.log(error);
    // })
    this.users = [
      {id: 1, firstName: "abc", lastName: "abc", dateOfBirth: new Date('03/22/4007'), username: 'ravster', password: 'red son' },
      {id: 2, firstName: "xyz", lastName: "xyz", dateOfBirth: new Date('04/22/4007'), username: 'ravster', password: 'red son' },
      {id: 3, firstName: "mno", lastName: "mno", dateOfBirth: new Date('05/22/4007'), username: 'ravster', password: 'red son' },
    ];
  }

  deleteUser(user) {
    console.log(user);
    // this._userService.deleteUser(user.id).subscribe((data) => {
      this.users.splice(this.users.indexOf(user),1);
    // }, (error) => {
    //   console.log(error);
    // });
  }
}
