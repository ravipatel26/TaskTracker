import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../services/task.service';
import { Task } from '../../models/task';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  private tasks:Task[];
  private userId:Number;

  constructor(private _taskService:TaskService) { }

  ngOnInit() {
    if (localStorage.getItem('currentUser')) {
      this.userId = JSON.parse(localStorage.getItem('currentUser')).id;
      this.updateTaskList();
    }
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
    console.log("task: " + task);
  }
}
