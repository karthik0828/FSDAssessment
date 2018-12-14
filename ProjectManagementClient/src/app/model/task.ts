import { ParentTask } from '../model/parent-task';
import { Project } from '../model/project';

export class Task {

    id:number;
    taskName:string;
    startDate:string;
    endDate:string;
    priority:number;
    status:string;
    user:string;   
    parent: ParentTask = new ParentTask();
    project: Project = new Project();
    
}

