package com.manage.project;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
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

import com.manage.project.dao.IParentRepository;
import com.manage.project.model.ParentTask;
import com.manage.project.model.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ParentRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IParentRepository parentRepository;

	@Test
	public void whenFindById_thenReturnParent() {
		// given
		ParentTask parent = new ParentTask();
		parent.setParentTaskName("Parent");
		List<Task> taskList = new ArrayList<Task>();
		parent.setTaskList(taskList);
		entityManager.persist(parent);
		entityManager.flush();

		// when
		Optional<ParentTask> optParent = parentRepository.findById(parent.getId());
		ParentTask sparent = optParent.get();

		// then
		assertThat(parent.getParentTaskName()).isEqualTo(sparent.getParentTaskName());
	}

	@Test
	public void saveParent() {
		// given
		ParentTask parent = new ParentTask();
		parent.setParentTaskName("Parent");
		List<Task> taskList = new ArrayList<Task>();
		parent.setTaskList(taskList);
		entityManager.persist(parent);
		entityManager.flush();

		// when
		ParentTask sparent = parentRepository.save(parent);

		// then
		assertThat(parent.getParentTaskName()).isEqualTo(sparent.getParentTaskName());
	}

	@Test
	public void deleteParent_ById() {
		// given
		ParentTask parent = new ParentTask();
		parent.setParentTaskName("Parent");
		List<Task> taskList = new ArrayList<Task>();
		parent.setTaskList(taskList);
		entityManager.persist(parent);
		entityManager.flush();

		// when
		parentRepository.deleteById(parent.getId());

		// then
		assertThat(parentRepository.findById(parent.getId())).isEmpty();

	}

	@Test
	public void whenFindAll_thenReturnParentList() {
		// given
		ParentTask parent = new ParentTask();
		parent.setParentTaskName("Parent");
		List<Task> taskList = new ArrayList<Task>();
		parent.setTaskList(taskList);
		entityManager.persist(parent);
		entityManager.flush();

		ParentTask parent2 = new ParentTask();
		parent2.setParentTaskName("Parent 2");
		List<Task> taskList2 = new ArrayList<Task>();
		parent2.setTaskList(taskList2);
		entityManager.persist(parent2);
		entityManager.flush();

		// when
		List<ParentTask> pList = null;
		pList = parentRepository.findAll();

		// then
		assertThat(pList.stream().filter(pnt -> "Parent".equalsIgnoreCase(pnt.getParentTaskName())).findFirst()
				.isPresent()).isEqualTo(true);
	}

}
