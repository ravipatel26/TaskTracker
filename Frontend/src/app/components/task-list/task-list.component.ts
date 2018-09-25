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

    this.createTask();
  }

  updateTaskList() {
    this._taskService.getPendingTasks(this.userId).subscribe((tasks) => {
      this.tasks = tasks;
    }, error => {
      console.log(error);
    })
  }

  completeTask(task) {
    this._taskService.completeTask(task).subscribe((data) => {
      this.updateTaskList();
    }, error => {
      console.log(error);
      alert("Task could not be completed!");
    })
  }

  createTask() {
    var task: Task = {
      id: 0,
      description: this.taskForm.controls.description.value,
      userId: this.userId,
      status: "pending"
    };

    this._taskService.createTask(task).subscribe((data) => {
      this.updateTaskList();
      this.submitted = false;
      this.taskForm.controls.description.setValue('');
    }, error => {
      console.log(error);
      alert("Task could not be added!");
    })
  }
}
