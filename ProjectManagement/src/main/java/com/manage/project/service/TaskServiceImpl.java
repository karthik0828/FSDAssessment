package com.manage.project.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.project.dao.IParentRepository;
import com.manage.project.dao.IProjectRepository;
import com.manage.project.dao.ITaskRepository;
import com.manage.project.model.ParentTask;
import com.manage.project.model.Project;
import com.manage.project.model.Task;

@Service
public class TaskServiceImpl implements ITaskService{

	@Autowired
	private ITaskRepository taskRepo;

	@Autowired
	private IParentRepository parentRepo;
	
	@Autowired
	private IProjectRepository projectRepo;

	@Transactional
	@Override
	public Task addTask(Task task) {
		// set parent task

		ParentTask pTask = null;
		if (task.getParent() != null) {
			List<ParentTask> parentList = parentRepo.findAll();
			for (ParentTask parentTask : parentList) {
				if (parentTask.getParentTaskName() != null
						&& parentTask.getParentTaskName().equalsIgnoreCase(task.getParent().getParentTaskName())) {
					pTask = parentTask;
				}
			}
			if (pTask == null) {
				pTask = new ParentTask();
				pTask.setParentTaskName(task.getParent().getParentTaskName());
				parentRepo.save(pTask);
			}
		}
		task.setParent(pTask);
	
		// set project
		Project proj = null;
		if (task.getProject() != null) {
			List<Project> projectList = projectRepo.findAll();
			for (Project project : projectList) {
				if (project.getProjectName() != null
						&& project.getProjectName().equalsIgnoreCase(task.getProject().getProjectName())) {
					proj = project;
				}
			}
			if (proj == null) {
				proj = new Project();
				proj.setProjectName(task.getProject().getProjectName());
				proj.setStatus("Open");
				projectRepo.save(proj);
			}
		}
		task.setProject(proj);
		
		task.setStatus("Open");
		return taskRepo.save(task);
	}


	@Transactional
	@Override
	public Task editTask(Task task) {

			// set parent task

			ParentTask pTask = null;
			if (task.getParent() != null) {
				List<ParentTask> parentList = parentRepo.findAll();
				for (ParentTask parentTask : parentList) {
					if (parentTask.getParentTaskName() != null
							&& parentTask.getParentTaskName().equalsIgnoreCase(task.getParent().getParentTaskName())) {
						pTask = parentTask;
					}
				}
				if (pTask == null) {
					pTask = new ParentTask();
					pTask.setParentTaskName(task.getParent().getParentTaskName());
					parentRepo.save(pTask);
				}
			}
			task.setParent(pTask);
		
			// set project

			Project proj = null;
			if (task.getProject() != null) {
				List<Project> projectList = projectRepo.findAll();
				for (Project project : projectList) {
					if (project.getProjectName() != null
							&& project.getProjectName().equalsIgnoreCase(task.getProject().getProjectName())) {
						proj = project;
					}
				}
				if (proj == null) {
					proj = new Project();
					proj.setProjectName(task.getProject().getProjectName());
					proj.setStatus("Open");
					projectRepo.save(proj);
				}
			}
			task.setProject(proj);		
		
		task.setStatus("Open");
		return taskRepo.save(task);
	}

	@Transactional
	@Override
	public void deleteTask(int taskid) {
		taskRepo.deleteById(taskid);
	}

	@Override
	public List<Task> viewTasks() {
		return taskRepo.findAll();
	}

	@Override
	public Task findTaskById(int taskid) {
		Optional<Task> optTask = taskRepo.findById(taskid);
		Task task = optTask.isPresent() ? optTask.get() : null;
		return task;
	}

	@Transactional
	@Override
	public Task endTask(Task task) {
		task.setStatus("Completed");
		return taskRepo.save(task);
	}

}
