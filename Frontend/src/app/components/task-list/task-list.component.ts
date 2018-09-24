import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../services/task.service';
import { Task } from '../../models/task';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  private tasks:Task[];
  private userId:Number;
  private taskForm: FormGroup;
  private submitted = false;

  constructor(private _taskService:TaskService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    if (localStorage.getItem('currentUser')) {
      this.userId = JSON.parse(localStorage.getItem('currentUser')).id;
      this.updateTaskList();
    }

    this.taskForm = this.formBuilder.group({
      description: ['', [Validators.required, Validators.maxLength(100)]]
    });
  }

  get form() {
    return this.taskForm.controls;
  }

  submitTask() {
    this.submitted = true;

    if (this.taskForm.invalid) {
      return;
    }

    //TODO: create task
  }

  updateTaskList() {
    this._taskService.getPendingTasks(this.userId).subscribe((tasks) => {
      console.log("tasks:\n" + tasks);
      this.tasks = tasks;
    }, error => {
      console.log(error);
    })
  }

  completeTask(task) {
    console.log("task: " + JSON.stringify(task));
    //TODO: update task
  }

  createTask() {
    
  }
}
