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

import com.manage.project.dao.IProjectRepository;
import com.manage.project.model.Project;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProjectRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IProjectRepository projectRepository;

	@Test
	public void whenFindById_thenReturnProject() {
		// given
		Project project = new Project();
		project.setProjectName("Project 1");
		entityManager.persist(project);
		entityManager.flush();

		// when
		Optional<Project> optProject = projectRepository.findById(project.getId());
		Project sProject = optProject.get();

		// then
		assertThat(project.getProjectName()).isEqualTo(sProject.getProjectName());
	}

	@Test
	public void saveProject() {
		// given
		Project project = new Project();
		project.setProjectName("Project 1");
		entityManager.persist(project);
		entityManager.flush();

		// when
		Project sProject = projectRepository.save(project);

		// then
		assertThat(project.getProjectName()).isEqualTo(sProject.getProjectName());

	}

	@Test
	public void deleteProject_ById() {
		// given
		Project project = new Project();
		project.setProjectName("Project 1");
		entityManager.persist(project);
		entityManager.flush();

		// when
		projectRepository.deleteById(project.getId());

		// then
		assertThat(projectRepository.findById(project.getId())).isEmpty();

	}

	@Test
	public void whenFindAll_thenReturnProjectList() {
		// given
		Project project = new Project();
		project.setProjectName("Project 1");
		entityManager.persist(project);
		entityManager.flush();

		Project project2 = new Project();
		project2.setProjectName("Project 2");
		entityManager.persist(project2);
		entityManager.flush();

		// when
		List<Project> pList = null;
		pList = projectRepository.findAll();

		// then
		assertThat(pList.stream().filter(proj -> "Project 1".equalsIgnoreCase(proj.getProjectName())).findFirst()
				.isPresent()).isEqualTo(true);
	}

}
