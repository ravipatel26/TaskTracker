import { Injectable } from '@angular/core';
import { Http, Response, Headers, Request, RequestOptions } from '@angular/http';
import { Observable, throwError } from 'rxjs';
import { Task } from '../models/task';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private baseUrl:string="http://localhost:8081";
  private headers = new Headers({'Content-Type':'application/json'});
  private options = new RequestOptions({headers:this.headers});

  constructor(private _http:Http) { }

  getPendingTasks(userId:Number) {
    return this._http.get(this.baseUrl + '/getPendingTasks/' + userId, this.options)
                     .pipe(
                        map((response:Response)=>response.json()),
                        catchError(this.errorHandler)
                      );
  }

  deleteTask(userId:Number) {
    return this._http.delete(this.baseUrl + '/deleteTask/' + userId, this.options)
                     .pipe(
                       map((response:Response)=>response),
                       catchError(this.errorHandler)
                      );
  }

  createTask(task:Task) {
    return this._http.post(this.baseUrl + '/createTask' , JSON.stringify(task), this.options)
                     .pipe(
                       map((response:Response)=>response),
                       catchError(this.errorHandler)
                      );
  }

  completeTask(task:Task) {
    return this._http.post(this.baseUrl + '/completeTask', JSON.stringify(task), this.options)
                     .pipe(
                       map((response:Response)=>response),
                       catchError(this.errorHandler)
                      );
  }

  errorHandler(error:Response) {
    return throwError(error);
  }
}
