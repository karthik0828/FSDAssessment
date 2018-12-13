package com.manage.project;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.manage.project.controller.ProjectController;
import com.manage.project.model.Project;
import com.manage.project.model.Task;
import com.manage.project.model.User;
import com.manage.project.service.IProjectService;
import com.manage.project.service.ITaskService;
import com.manage.project.service.IUserService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProjectController.class)
public class ProjectControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private ITaskService taskService;

	@MockBean
	private IProjectService projectService;

	@MockBean
	private IUserService userService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testViewAllTasks() throws Exception {
		Task task = new Task();
		task.setTaskName("Timesheet");

		List<Task> allTasks = singletonList(task);

		given(taskService.viewTasks()).willReturn(allTasks);

		mockMvc.perform(get("/projapp/alltasks")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].taskName", is(task.getTaskName())));
		verify(taskService, times(1)).viewTasks();
		verifyNoMoreInteractions(taskService);
	}

	@Test
	public void testAddNewTask() throws Exception {

		Task task = new Task();
		task.setTaskName("Task 1");
		given(taskService.addTask(task)).willReturn(task);

		mockMvc.perform(MockMvcRequestBuilders.post("/projapp/newtask").contentType(MediaType.APPLICATION_JSON)
				.content("{\"taskName\" :\"Task 1\"}").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testEditTask() throws Exception {

		Task task = new Task();
		task.setTaskName("Task 1");
		given(taskService.editTask(task)).willReturn(task);

		mockMvc.perform(MockMvcRequestBuilders.put("/projapp/updatetask").contentType(MediaType.APPLICATION_JSON)
				.content("{\"taskName\" :\"Task 1\"}").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testEndTask() throws Exception {

		Task task = new Task();
		task.setTaskName("Task 1");
		given(taskService.endTask(task)).willReturn(task);

		mockMvc.perform(MockMvcRequestBuilders.put("/projapp/endtask").contentType(MediaType.APPLICATION_JSON)
				.content("{\"taskName\" :\"Task 1\"}").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void verifyDeleteTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/projapp/deletetask/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void verifyGetTaskById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/projapp/findtask/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testViewAllProjects() throws Exception {
		Project project = new Project();
		project.setProjectName("Project 1");

		List<Project> allProjects = singletonList(project);

		given(projectService.viewProjects()).willReturn(allProjects);

		mockMvc.perform(get("/projapp/allprojects")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].projectName", is(project.getProjectName())));
		verify(projectService, times(1)).viewProjects();
		verifyNoMoreInteractions(projectService);
	}

	@Test
	public void testAddNewProject() throws Exception {

		Project project = new Project();
		project.setProjectName("Project 1");
		given(projectService.addProject(project)).willReturn(project);

		mockMvc.perform(MockMvcRequestBuilders.post("/projapp/newproject").contentType(MediaType.APPLICATION_JSON)
				.content("{\"projectName\" :\"Project 1\"}").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void testEditProject() throws Exception {

		Project project = new Project();
		project.setProjectName("Project 1");
		given(projectService.editProject(project)).willReturn(project);

		mockMvc.perform(MockMvcRequestBuilders.put("/projapp/updateproject").contentType(MediaType.APPLICATION_JSON)
				.content("{\"projectName\" :\"Project 1\"}").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void testEndProject() throws Exception {

		Project project = new Project();
		project.setProjectName("Project 1");
		given(projectService.endProject(project)).willReturn(project);

		mockMvc.perform(MockMvcRequestBuilders.put("/projapp/endproject").contentType(MediaType.APPLICATION_JSON)
				.content("{\"projectName\" :\"Project 1\"}").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void verifyDeleteProject() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/projapp/deleteproject/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void verifyGetProjectById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/projapp/findproject/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testViewAllUsers() throws Exception {
		User user = new User();
		user.setFirstName("User 1");

		List<User> allUsers = singletonList(user);

		given(userService.viewUsers()).willReturn(allUsers);

		mockMvc.perform(get("/projapp/allusers")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].firstName", is(user.getFirstName())));
		verify(userService, times(1)).viewUsers();
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testAddNewUser() throws Exception {

		User user = new User();
		user.setFirstName("User 1");
		given(userService.addUser(user)).willReturn(user);

		mockMvc.perform(MockMvcRequestBuilders.post("/projapp/newuser").contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\" :\"User 1\"}").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testEditUser() throws Exception {

		User user = new User();
		user.setFirstName("User 1");
		given(userService.editUser(user)).willReturn(user);

		mockMvc.perform(MockMvcRequestBuilders.put("/projapp/updateuser").contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\" :\"User 1\"}").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void verifyDeleteUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/projapp/deleteuser/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void verifyGetUserById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/projapp/finduser/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
