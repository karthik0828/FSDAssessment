import { Component, OnInit } from '@angular/core';
import { FormBuilder ,FormGroup } from '@angular/forms';
import { ProjMngService } from '../service/proj-mng.service';
import { Router, ActivatedRoute} from '@angular/router';
import { Project } from '../model/project';

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {

  form: FormGroup;
  private projects : Project[] =[];
  project : Project = new Project();
  id :string;
  title :string;

  constructor(
    formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private projMngService : ProjMngService) {
      
      this.form = formBuilder.group({
        projectName :[],       
        startDate :[],
        endDate :[],
        priority :[],
        manager :[]        
      }); 

     }

  ngOnInit() {
    this.projMngService.viewprojects()
    .subscribe((data:Project[]) => this.projects = data);

    var id = this.route.params.subscribe(params => {
      var id = params['id'];
      this.title = id ? 'EDIT PROJECT' : 'ADD NEW PROJECT';
      this.id = id;      
      if (!id)
      return;

    this.projMngService.getproject(id)
      .subscribe(
       (project : Project) => this.project = project,
        response => {
          if (response.status == 404) {
            this.router.navigate(['NotFound']);
          }
        });
  });
  }

  addNewProject() {
    var id = this.route.params.subscribe(params => {
      var id = params['id'];
      this.id = id;      
    if (!id){
      var projectvalue = this.form.value;
      this.projMngService.addproject(projectvalue)
      .subscribe(data => this.router.navigate(['viewprojects'])); 
    }else{
      var projectvalue = this.form.value;
      this.projMngService.updateproject(projectvalue,this.id)
      .subscribe(data => this.router.navigate(['addproject']));
    }      
    });
 
  }


  endProject(project){
      if (confirm("Are you sure want to end the project " + project.projectName + "?")) {
        this.projMngService.endproject(project)
        .subscribe((project : Project) => {
        this.project = project;        
        });          
      
     }
    }

}
