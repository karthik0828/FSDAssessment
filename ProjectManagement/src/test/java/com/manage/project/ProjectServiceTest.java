package com.manage.project;

import static org.junit.Assert.assertEquals;
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

import com.manage.project.dao.IProjectRepository;
import com.manage.project.model.Project;
import com.manage.project.model.Task;
import com.manage.project.service.ProjectServiceImpl;

@RunWith(SpringRunner.class)
public class ProjectServiceTest {

	@Mock
	private IProjectRepository projRepo;

	@InjectMocks
	private ProjectServiceImpl projService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testViewsProjects() {
		List<Project> projList = new ArrayList<Project>();
		Project project1 = new Project();
		project1.setProjectName("Project 1");
		projList.add(project1);
		Project project2 = new Project();
		project2.setProjectName("Project 2");
		projList.add(project2);

		when(projRepo.findAll()).thenReturn(projList);
		List<Project> result = projService.viewProjects();
		assertEquals(2, result.size());
	}

	@Test
	public void testAddProject() {

		Project project1 = new Project();
		project1.setProjectName("Project 1");
		project1.setStartDate("2018-01-01");
		project1.setEndDate("2018-12-31");
		project1.setPriority(20);
		project1.setManager("Manager 1");
		List<Task> taskList = new ArrayList<Task>();
		project1.setTaskList(taskList);

		when(projRepo.save(project1)).thenReturn(project1);
		Project project = projService.addProject(project1);
		assertEquals("Project 1", project.getProjectName());

	}

	@Test
	public void testDeleteProject() {
		Project project1 = new Project();
		project1.setProjectName("Project 1");
		projService.deleteProject(1);
		verify(projRepo, times(1)).deleteById(1);
	}

	@Test
	public void test1EditProject() {

		Project project1 = new Project();
		project1.setProjectName("Project 1");
		List<Task> taskList = new ArrayList<Task>();
		project1.setTaskList(taskList);

		when(projRepo.save(project1)).thenReturn(project1);
		Project project = projService.editProject(project1);
		assertEquals("Project 1", project.getProjectName());

	}

	@Test
	public void test2EditProject() {

		Project project1 = new Project();
		project1.setProjectName("Project 1");

		when(projRepo.save(project1)).thenReturn(project1);
		Project project = projService.editProject(project1);
		assertEquals("Project 1", project.getProjectName());

	}

	@Test
	public void findProjectById() {

		Project project1 = new Project();
		project1.setProjectName("Project 1");

		when(projRepo.findById(1)).thenReturn(Optional.of(project1));
		Project project = projService.findProjectById(1);
		assertEquals("Project 1", project.getProjectName());

	}

	@Test
	public void test1EndProject() {

		Project project1 = new Project();
		project1.setProjectName("Project 1");
		project1.setStatus("Completed");

		when(projRepo.save(project1)).thenReturn(project1);
		Project project = projService.endProject(project1);
		assertEquals("Completed", project.getStatus());

	}

	@Test
	public void test2EndProject() {

		Project project1 = new Project();
		project1.setProjectName("Project 1");
		project1.setStatus("Completed");
		List<Task> taskList = new ArrayList<Task>();
		project1.setTaskList(taskList);

		when(projRepo.save(project1)).thenReturn(project1);
		Project project = projService.endProject(project1);
		assertEquals("Completed", project.getStatus());

	}

}
