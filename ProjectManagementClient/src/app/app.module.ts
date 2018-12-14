import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule , ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes} from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { EditTaskComponent } from './edit-task/edit-task.component';
import { ViewTasksComponent } from './view-tasks/view-tasks.component';
import { AddProjectComponent } from './add-project/add-project.component';
import { AddUserComponent } from './add-user/add-user.component';
import { HomeComponent } from './home/home.component';
import { TaskMngService } from './service/task-mng.service';
import { ProjMngService } from './service/proj-mng.service';
import { UserMngService } from './service/user-mng.service';

const appRoutes : Routes = [
  {path: '', pathMatch: 'full',component: HomeComponent},
  {path:'home' , component:HomeComponent},
  {path:'viewtasks' ,component:ViewTasksComponent},
  {path:'viewprojects' ,component:AddProjectComponent},
  {path:'viewusers' ,component:AddUserComponent},
  {path:'addtask' ,component:AddTaskComponent},
  {path:'addproject' ,component:AddProjectComponent},
  {path:'adduser' ,component:AddUserComponent},
  {path:'edittask/:id' , component:EditTaskComponent},
  {path:'editproject/:id' ,component:AddProjectComponent},
  {path:'edituser/:id' ,component:AddUserComponent},
  {path:'endproject' ,component:AddProjectComponent},
  {path:'deleteuser' ,component:AddUserComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AddTaskComponent,
    EditTaskComponent,
    ViewTasksComponent,
    AddProjectComponent,   
    AddUserComponent,
    HomeComponent
        
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [TaskMngService,ProjMngService,UserMngService],
  bootstrap: [AppComponent]
})
export class AppModule { }
