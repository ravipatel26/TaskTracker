import { Injectable } from '@angular/core';
import { Http, Response, Headers, Request, RequestOptions } from '@angular/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private baseUrl:string="http://localhost:8080";
  private headers = new Headers({'Content-Type':'application/json'});
  private options = new RequestOptions({headers:this.headers});

  constructor(private _http:Http) { }

  login(username: string, password: string) {
    return this._http.post(this.baseUrl + '/login', { "username": username, "password": password }, this.options)
                     .pipe(
                        map((response:Response)=>response.json()),
                        catchError(this.errorHandler)
                      );
  }

  logout() {
      // remove user from local storage to log user out
      localStorage.removeItem('currentUser');
  }

  errorHandler(error:Response) {
    return throwError(error);
  }
}