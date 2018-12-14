import { Component, OnInit } from '@angular/core';
import { FormBuilder ,FormGroup } from '@angular/forms';
import { TaskMngService } from '../service/task-mng.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Task } from '../model/task';
import { ParentTask } from '../model/parent-task';
import { Project } from '../model/project';

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent implements OnInit {

  form: FormGroup;
  task : Task = new Task();
  parent : ParentTask = new ParentTask();
  project : Project = new Project();
  id :string;

  constructor(
    formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
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
    var id = this.route.params.subscribe(params => {
        var id = params['id'];
        this.id = id;      
      if (!id)
        return;

      this.taskMngService.gettask(id)
        .subscribe(
         (task : Task) => this.task = task,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
    });
  }

  saveTask() {
     var taskvalue = this.form.value;
     this.taskMngService.updatetask(taskvalue,this.id)
     .subscribe(data => this.router.navigate(['viewtasks']));  
    }  

}
