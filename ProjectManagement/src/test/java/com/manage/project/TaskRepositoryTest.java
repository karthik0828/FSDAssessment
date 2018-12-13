package com.manage.project;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.manage.project.dao.ITaskRepository;
import com.manage.project.model.ParentTask;
import com.manage.project.model.Project;
import com.manage.project.model.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TaskRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ITaskRepository taskRepository;

	@Test
	public void whenFindById_thenReturnTask() {
		// given

		ParentTask parent1 = new ParentTask();
		parent1.setParentTaskName("Parent 1");
		entityManager.persist(parent1);
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		entityManager.persist(task1);
		entityManager.flush();

		// when
		Optional<Task> opttask = taskRepository.findById(task1.getId());
		Task stask = opttask.get();

		// then
		assertThat(stask.getTaskName()).isEqualTo(task1.getTaskName());
	}

	@Test
	public void saveTask() {
		// given
		ParentTask parent1 = new ParentTask();
		parent1.setParentTaskName("Parent 1");
		entityManager.persist(parent1);
		Project project1 = new Project();
		project1.setProjectName("Project 1");
		entityManager.persist(project1);
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		task1.setUser("User 1");
		entityManager.persist(task1);
		entityManager.flush();

		// when
		Task stask = taskRepository.save(task1);

		// then
		assertThat(stask.getTaskName()).isEqualTo(task1.getTaskName());
	}

	@Test
	public void deleteTask_ById() {
		// given
		ParentTask parent1 = new ParentTask();
		parent1.setParentTaskName("Parent 1");
		entityManager.persist(parent1);
		Project project1 = new Project();
		project1.setProjectName("Project 1");
		entityManager.persist(project1);
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		task1.setUser("User 1");
		entityManager.persist(task1);
		entityManager.flush();

		// when
		taskRepository.deleteById(task1.getId());

		// then
		assertThat(taskRepository.findById(task1.getId())).isEmpty();

	}

	@Test
	public void whenFindAll_thenReturnBookList() {
		// given
		ParentTask parent1 = new ParentTask();
		parent1.setParentTaskName("Parent 1");
		entityManager.persist(parent1);
		Project project1 = new Project();
		project1.setProjectName("Project 1");
		entityManager.persist(project1);
		Task task1 = new Task();
		task1.setTaskName("Task 1");
		task1.setStartDate("2018-01-01");
		task1.setEndDate("2018-12-31");
		task1.setPriority(10);
		task1.setUser("User 1");
		entityManager.persist(task1);
		entityManager.flush();

		ParentTask parent2 = new ParentTask();
		parent2.setParentTaskName("Parent 2");
		entityManager.persist(parent2);
		Project project2 = new Project();
		project2.setProjectName("Project 2");
		entityManager.persist(project2);
		Task task2 = new Task();
		task2.setTaskName("Task 2");
		task2.setStartDate("2018-01-01");
		task2.setEndDate("2018-12-31");
		task2.setPriority(10);
		task2.setUser("User 2");
		entityManager.persist(task2);
		entityManager.flush();

		// when
		List<Task> taskList = taskRepository.findAll();

		// then
		assertThat(taskList.get(0).getTaskName()).isEqualTo("Task 1");
	}

}
