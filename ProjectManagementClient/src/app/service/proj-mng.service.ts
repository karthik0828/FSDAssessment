import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class ProjMngService {

  constructor(private http:HttpClient) { }
  
    private viewProjUrl : string ="/ProjectManagement/projapp/allprojects";
    private addUrl : string ="/ProjectManagement/projapp/newproject";
    private updateUrl : string ="/ProjectManagement/projapp/updateproject";
    private deleteUrl : string ="/ProjectManagement/projapp/deleteproject";
    private getUrl : string = "/ProjectManagement/projapp/findproject";
    private endUrl : string = "/ProjectManagement/projapp/endproject";
  
    viewprojects(){
        return this.http.get(this.viewProjUrl)
        .pipe(map(res => res))
    }
  
    addproject(project){
         return this.http.post(this.addUrl,project)
         .pipe(map(res => res));
    }
  
    updateproject(project,id){
      project.id = id;
     return this.http.put(this.updateUrl,project)
     .pipe(map(res => res));
   }
  
   deleteproject(project){
     return this.http.delete(this.deleteUrl+"/"+project.id,{observe: 'response'});
     //.pipe(map(res => res.status));
   }
  
   getproject(id){
     return this.http.get(this.getUrl+"/"+id)
     .pipe(map(res => res));
   }
  
   endproject(project){
     return this.http.put(this.endUrl,project)
     .pipe(map(res => res));
   }
  }