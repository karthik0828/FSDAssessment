import { Component, OnInit } from '@angular/core';
import { FormBuilder ,FormGroup } from '@angular/forms';
import { UserMngService } from '../service/user-mng.service';
import { Router, ActivatedRoute} from '@angular/router';
import { User } from '../model/user';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  form: FormGroup;
  private users : User[] =[];
  user : User = new User();
  id :string;
  title :string;

  constructor(
    formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private userMngService : UserMngService) {
      
      this.form = formBuilder.group({
        firstName :[], 
        lastName :[],      
        empId :[]       
      }); 

     }

  ngOnInit() {
    this.userMngService.viewusers()
    .subscribe((data:User[]) => this.users = data);

    var id = this.route.params.subscribe(params => {
      var id = params['id'];
      this.title = id ? 'EDIT USER' : 'ADD NEW USER';
      this.id = id;      
      if (!id)
      return;

    this.userMngService.getuser(id)
      .subscribe(
       (user : User) => this.user = user,
        response => {
          if (response.status == 404) {
            this.router.navigate(['NotFound']);
          }
        });
  });
  }

  addNewUser() {
    var id = this.route.params.subscribe(params => {
      var id = params['id'];
      this.id = id;      
    if (!id){
      var uservalue = this.form.value;
      this.userMngService.adduser(uservalue)
      .subscribe(data => this.router.navigate(['viewusers'])); 
    }else{
      var uservalue = this.form.value;
      this.userMngService.updateuser(uservalue,this.id)
      .subscribe(data => this.router.navigate(['adduser']));
    }      
    });
 
  }


  deleteUser(user){
    if (confirm("Are you sure want to delete " + user.firstName + "?")) {
  
      this.userMngService.deleteuser(user)
      .subscribe(success => {   
        this.getlatestView(); 
        });   
 
 
    }
  }
 
  getlatestView(){
    this.userMngService.viewusers()
    .subscribe((data :User[]) => this.users = data)
  }

}
