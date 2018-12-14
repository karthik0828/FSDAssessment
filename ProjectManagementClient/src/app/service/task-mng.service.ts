import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class TaskMngService {

  constructor(private http:HttpClient) { }

  private viewTaskUrl : string ="/ProjectManagement/projapp/alltasks";
  private addUrl : string ="/ProjectManagement/projapp/newtask";
  private updateUrl : string ="/ProjectManagement/projapp/updatetask";
  private deleteUrl : string ="/ProjectManagement/projapp/deletetask";
  private getUrl : string = "/ProjectManagement/projapp/findtask";
  private endUrl : string = "/ProjectManagement/projapp/endtask";

  viewtasks(){
      return this.http.get(this.viewTaskUrl)
      .pipe(map(res => res))
  }

  addtask(task){
       return this.http.post(this.addUrl,task)
       .pipe(map(res => res));
  }

  updatetask(task,id){
   task.id = id;
   return this.http.put(this.updateUrl,task)
   .pipe(map(res => res));
 }

 deletetask(task){
   return this.http.delete(this.deleteUrl+"/"+task.id,{observe: 'response'});
   //.pipe(map(res => res));
 }

 gettask(id){
   return this.http.get(this.getUrl+"/"+id)
   .pipe(map(res => res));
 }

 endtask(task){
   return this.http.put(this.endUrl,task)
   .pipe(map(res => res));
 }
}
