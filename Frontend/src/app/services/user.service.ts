import { Injectable } from '@angular/core';
import { Http, Response, Headers, Request, RequestOptions } from '@angular/http';
import { Observable, throwError } from 'rxjs';
import { User } from '../models/user';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  private baseUrl:string="http://localhost:8080";
  private headers = new Headers({'Content-Type':'application/json'});
  private options = new RequestOptions({headers:this.headers});
  
  constructor(private _http:Http) { }

  getUsers() {
    return this._http.get(this.baseUrl + '/getUsers', this.options)
                     .pipe(
                        map((response:Response)=>response.json()),
                        catchError(this.errorHandler)
                      );
  }

  getUser(id:Number) {
    return this._http.get(this.baseUrl + '/getUser/' + id , this.options)
                     .pipe(
                       map((response:Response)=>response.json()),
                       catchError(this.errorHandler)
                      );
  }

  deleteUser(id:Number) {
    return this._http.delete(this.baseUrl + '/deleteUser/' + id , this.options)
                     .pipe(
                       map((response:Response)=>response.json()),
                       catchError(this.errorHandler)
                      );
  }

  createUser(user:User) {
    return this._http.post(this.baseUrl + '/createUser' , JSON.stringify(user), this.options)
                     .pipe(
                       map((response:Response)=>response.json()),
                       catchError(this.errorHandler)
                      );
  }

  editUser(user:User) {
    return this._http.post(this.baseUrl + '/editUser' , JSON.stringify(user), this.options)
                     .pipe(
                       map((response:Response)=>response.json()),
                       catchError(this.errorHandler)
                      );
  }

  errorHandler(error:Response) {
    return throwError(error);
  }
}
