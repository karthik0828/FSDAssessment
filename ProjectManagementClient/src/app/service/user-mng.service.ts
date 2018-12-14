import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class UserMngService {

  constructor(private http:HttpClient) { }
  
    private viewUserUrl : string ="/ProjectManagement/projapp/allusers";
    private addUrl : string ="/ProjectManagement/projapp/newuser";
    private updateUrl : string ="/ProjectManagement/projapp/updateuser";
    private deleteUrl : string ="/ProjectManagement/projapp/deleteuser";
    private getUrl : string = "/ProjectManagement/projapp/finduser";
  
    viewusers(){
        return this.http.get(this.viewUserUrl)
        .pipe(map(res => res))
    }
  
    adduser(user){
         return this.http.post(this.addUrl,user)
         .pipe(map(res => res));
    }
  
    updateuser(user,id){
      user.id = id;
     return this.http.put(this.updateUrl,user)
     .pipe(map(res => res));
   }
  
   deleteuser(user){
     return this.http.delete(this.deleteUrl+"/"+user.id,{observe: 'response'});
     //.pipe(map(res => res.status));
   }
  
   getuser(id){
     return this.http.get(this.getUrl+"/"+id)
     .pipe(map(res => res));
   }
  
   }
