package com.manage.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.manage.project.dao.IParentRepository;
import com.manage.project.dao.IProjectRepository;
import com.manage.project.dao.ITaskRepository;
import com.manage.project.dao.IUserRepository;
import com.manage.project.model.ParentTask;
import com.manage.project.model.Project;
import com.manage.project.model.Task;
import com.manage.project.service.TaskServiceImpl;

@RunWith(SpringRunner.class)
public class TaskServiceTest {

	@Mock
	private ITaskRepository taskRepo;
	@Mock
	private IParentRepository parentRepo;
	@Mock
	private IProjectRepository projRepo;
	@Mock
	private IUserRepository userRepo;

	@InjectMocks
	private TaskServiceImpl taskService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testViewTasks() {
		List<Task> taskList = new ArrayList<Task>();
		ParentTask parent1 = new ParentTask();
		parent1.setParentTaskName("Parent 1");
		Project project1 = new Project();
		project1.setProjectName("Project 1");
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		task1.setUser("User 1");
		taskList.add(task1);

		ParentTask parent2 = new ParentTask();
		parent2.setParentTaskName("Parent 2");
		Project project2 = new Project();
		project2.setProjectName("Project 2");
		Task task2 = new Task();
		task2.setTaskName("Task 2");
		task2.setStartDate("2018-01-01");
		task2.setEndDate("2018-12-31");
		task2.setPriority(10);
		task2.setUser("User 2");
		taskList.add(task2);

		when(taskRepo.findAll()).thenReturn(taskList);
		List<Task> result = taskService.viewTasks();
		assertEquals(2, result.size());
	}

	@Test
	public void testAddTask() {

		List<ParentTask> parentList = new ArrayList<ParentTask>();
		List<Project> projList = new ArrayList<Project>();
		List<Task> tList = new ArrayList<Task>();
		ParentTask parent1 = new ParentTask();
		parent1.setParentTaskName("Parent 1");
		Project project1 = new Project();
		project1.setProjectName("Project 1");
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		task1.setUser("User 1");
		task1.setParent(parent1);
		task1.setProject(project1);
		tList.add(task1);
		parent1.setTaskList(tList);
		parentList.add(parent1);
		projList.add(project1);

		when(parentRepo.findAll()).thenReturn(parentList);
		when(projRepo.findAll()).thenReturn(projList);
		when(taskRepo.save(task1)).thenReturn(task1);
		Task task = taskService.addTask(task1);
		assertEquals("Task 1", task.getTaskName());
		assertEquals(task1.getParent().getTaskList().size(), 1);

	}

	@Test
	public void testNegAddTask() {

		List<ParentTask> parentList = new ArrayList<ParentTask>();
		List<Project> projList = new ArrayList<Project>();
		ParentTask parent1 = new ParentTask();
		parent1.setParentTaskName("Parent 1");
		ParentTask parent2 = new ParentTask();
		parent2.setParentTaskName("Parent 2");
		Project project1 = new Project();
		project1.setProjectName("Project 1");
		Project project2 = new Project();
		project2.setProjectName("Project 2");
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		task1.setUser("User 1");
		task1.setParent(parent1);
		task1.setProject(project1);
		parentList.add(parent2);
		projList.add(project2);

		when(parentRepo.findAll()).thenReturn(parentList);
		when(parentRepo.save(parent1)).thenReturn(parent1);
		when(projRepo.findAll()).thenReturn(projList);
		when(projRepo.save(project1)).thenReturn(project1);
		when(taskRepo.save(task1)).thenReturn(task1);
		Task task = taskService.addTask(task1);
		assertEquals("Task 1", task.getTaskName());

	}

	@Test
	public void testDeleteTask() {
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		task1.setUser("User 1");
		taskService.deleteTask(1);
		verify(taskRepo, times(1)).deleteById(1);
	}

	@Test
	public void testEditTask() {

		List<ParentTask> parentList = new ArrayList<ParentTask>();
		List<Project> projList = new ArrayList<Project>();
		ParentTask parent1 = new ParentTask();
		parent1.setParentTaskName("Parent 1");
		Project project1 = new Project();
		project1.setProjectName("Project 1");
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		task1.setUser("User 1");
		task1.setParent(parent1);
		task1.setProject(project1);
		parentList.add(parent1);
		projList.add(project1);

		when(taskRepo.findById(1)).thenReturn(Optional.of(task1));
		assertTrue(Optional.of(task1).isPresent());
		when(parentRepo.findAll()).thenReturn(parentList);
		when(projRepo.findAll()).thenReturn(projList);
		when(taskRepo.save(task1)).thenReturn(task1);
		Task task = taskService.editTask(task1);
		assertEquals("Task 1", task.getTaskName());

	}

	@Test
	public void testNegEditTask() {

		List<ParentTask> parentList = new ArrayList<ParentTask>();
		List<Project> projList = new ArrayList<Project>();
		ParentTask parent1 = new ParentTask();
		parent1.setParentTaskName("Parent 1");
		ParentTask parent2 = new ParentTask();
		parent2.setParentTaskName("Parent 2");
		Project project1 = new Project();
		project1.setProjectName("Project 1");
		Project project2 = new Project();
		project2.setProjectName("Project 2");
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		task1.setUser("User 1");
		task1.setParent(parent1);
		task1.setProject(project1);
		parentList.add(parent2);
		projList.add(project2);

		when(parentRepo.findAll()).thenReturn(parentList);
		when(parentRepo.save(parent1)).thenReturn(parent1);
		when(projRepo.findAll()).thenReturn(projList);
		when(projRepo.save(project1)).thenReturn(project1);
		when(taskRepo.save(task1)).thenReturn(task1);
		Task task = taskService.editTask(task1);
		assertEquals("Task 1", task.getTaskName());

	}

	@Test
	public void testFindTaskById() {

		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		task1.setUser("User 1");

		when(taskRepo.findById(1)).thenReturn(Optional.of(task1));
		Task task = taskService.findTaskById(1);
		assertEquals("Task 1", task.getTaskName());

	}

	@Test
	public void testEndTask() {

		List<ParentTask> parentList = new ArrayList<ParentTask>();
		List<Project> projList = new ArrayList<Project>();
		ParentTask parent1 = new ParentTask();
		parent1.setParentTaskName("Parent 1");
		Project project1 = new Project();
		project1.setProjectName("Project 1");
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		task1.setUser("User 1");
		task1.setStatus("Completed");
		task1.setParent(parent1);
		task1.setProject(project1);
		parentList.add(parent1);
		projList.add(project1);

		when(taskRepo.save(task1)).thenReturn(task1);
		Task task = taskService.endTask(task1);
		assertEquals("Completed", task.getStatus());

	}

}
