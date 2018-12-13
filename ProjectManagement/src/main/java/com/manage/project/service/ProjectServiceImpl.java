package com.manage.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.project.dao.IProjectRepository;
import com.manage.project.model.Project;
import com.manage.project.model.Task;

@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private IProjectRepository projectRepo;

	@Override
	@Transactional
	public Project addProject(Project project) {
		project.setStatus("Open");
		return projectRepo.save(project);
	}

	@Override
	@Transactional
	public Project editProject(Project project) {
		project.setStatus("Open");
		if (project.getTaskList() == null) {
			List<Task> taskList = new ArrayList<Task>();
			project.setTaskList(taskList);
		} else {
			List<Task> taskList = project.getTaskList();
			project.getTaskList().clear();
			project.getTaskList().addAll(taskList);
		}

		return projectRepo.save(project);
	}

	@Override
	@Transactional
	public void deleteProject(int projectid) {
		projectRepo.deleteById(projectid);
	}

	@Override
	public List<Project> viewProjects() {
		return projectRepo.findAll();
	}

	@Override
	public Project findProjectById(int projectid) {
		Optional<Project> optProject = projectRepo.findById(projectid);
		Project project = optProject.isPresent() ? optProject.get() : null;
		return project;
	}

	@Override
	@Transactional
	public Project endProject(Project project) {
		project.setStatus("Completed");
		if (project.getTaskList() == null) {
			List<Task> taskList = new ArrayList<Task>();
			project.setTaskList(taskList);
		} else {
			List<Task> taskList = project.getTaskList();
			project.getTaskList().clear();
			project.getTaskList().addAll(taskList);
		}
		return projectRepo.save(project);
	}

}
