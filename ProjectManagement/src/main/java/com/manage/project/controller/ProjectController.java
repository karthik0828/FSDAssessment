package com.manage.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.project.model.Project;
import com.manage.project.model.Task;
import com.manage.project.model.User;
import com.manage.project.service.IProjectService;
import com.manage.project.service.ITaskService;
import com.manage.project.service.IUserService;

@RestController
@RequestMapping("/projapp")
public class ProjectController {

	@Autowired
	private ITaskService taskService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IUserService userService;

	// tasks functions
	@GetMapping("/alltasks")
	public ResponseEntity<List<Task>> viewAllTasks() {
		return new ResponseEntity<List<Task>>(taskService.viewTasks(), HttpStatus.OK);
	}

	@PostMapping("/newtask")
	public ResponseEntity<Task> addNewTask(@RequestBody Task task) {
		return new ResponseEntity<Task>(taskService.addTask(task), HttpStatus.OK);
	}

	@PutMapping("/updatetask")
	public ResponseEntity<Task> editTask(@RequestBody Task task) {
		return new ResponseEntity<Task>(taskService.editTask(task), HttpStatus.OK);
	}

	@DeleteMapping("/deletetask/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable(value = "id") int id) {
		ResponseEntity<Void> response = null;
		taskService.deleteTask(id);
		response = new ResponseEntity<>(HttpStatus.OK);
		return response;
	}

	@GetMapping("/findtask/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") int id) {
		return new ResponseEntity<Task>(taskService.findTaskById(id), HttpStatus.OK);
	}

	@PutMapping("/endtask")
	public ResponseEntity<Task> endTask(@RequestBody Task task) {
		return new ResponseEntity<Task>(taskService.endTask(task), HttpStatus.OK);
	}

	// projects functions

	@GetMapping("/allprojects")
	public ResponseEntity<List<Project>> viewAllProjects() {
		return new ResponseEntity<List<Project>>(projectService.viewProjects(), HttpStatus.OK);
	}

	@PostMapping("/newproject")
	public ResponseEntity<Project> addNewProject(@RequestBody Project project) {
		return new ResponseEntity<Project>(projectService.addProject(project), HttpStatus.OK);
	}

	@PutMapping("/updateproject")
	public ResponseEntity<Project> editProject(@RequestBody Project project) {
		return new ResponseEntity<Project>(projectService.editProject(project), HttpStatus.OK);
	}

	@DeleteMapping("/deleteproject/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable(value = "id") int id) {
		ResponseEntity<Void> response = null;
		projectService.deleteProject(id);
		response = new ResponseEntity<>(HttpStatus.OK);
		return response;
	}

	@GetMapping("/findproject/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") int id) {
		return new ResponseEntity<Project>(projectService.findProjectById(id), HttpStatus.OK);
	}

	@PutMapping("/endproject")
	public ResponseEntity<Project> endProject(@RequestBody Project project) {
		return new ResponseEntity<Project>(projectService.endProject(project), HttpStatus.OK);
	}

	// users functions

	@GetMapping("/allusers")
	public ResponseEntity<List<User>> viewAllUsers() {
		return new ResponseEntity<List<User>>(userService.viewUsers(), HttpStatus.OK);
	}

	@PostMapping("/newuser")
	public ResponseEntity<User> addNewUser(@RequestBody User user) {

		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
	}

	@PutMapping("/updateuser")
	public ResponseEntity<User> editUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.editUser(user), HttpStatus.OK);
	}

	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") int id) {
		ResponseEntity<Void> response = null;
		userService.deleteUser(id);
		response = new ResponseEntity<>(HttpStatus.OK);
		return response;
	}

	@GetMapping("/finduser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") int id) {
		return new ResponseEntity<User>(userService.findUserById(id), HttpStatus.OK);
	}

}
