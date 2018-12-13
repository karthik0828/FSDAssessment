package com.manage.project.service;

import java.util.List;

import com.manage.project.model.Project;

public interface IProjectService {
	
	public Project addProject(Project project);
	public Project editProject(Project project);
	public void deleteProject(int projectid);
	public List<Project> viewProjects();
	public Project findProjectById(int projectid);
	public Project endProject(Project project);

}
