import { Component, OnInit } from '@angular/core';
import { FormBuilder ,FormGroup } from '@angular/forms';
import { TaskMngService } from '../service/task-mng.service';
import { Router } from '@angular/router';
import { Task } from '../model/task';
import { ParentTask } from '../model/parent-task';
import { Project } from '../model/project';


@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {

  form: FormGroup;
  task : Task = new Task();
  parent : ParentTask = new ParentTask();
  project : Project = new Project(); 
  

  constructor(
    formBuilder: FormBuilder,
    private router: Router,
    private taskMngService : TaskMngService) {
      
      this.form = formBuilder.group({
        taskName :[],       
        startDate :[],
        endDate :[],
        priority :[],      
        parent: formBuilder.group({
          parentTaskName :[]
        }),
        project: formBuilder.group({
          projectName :[]
        }),
        user :[]
      }); 

     }

  ngOnInit() {
  }

  addNewTask() {    
     var taskvalue = this.form.value;
     this.taskMngService.addtask(taskvalue)
     .subscribe(data => this.router.navigate(['viewtasks']));  
    } 
  
}
