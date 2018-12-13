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

import com.manage.project.dao.IUserRepository;
import com.manage.project.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IUserRepository userRepository;

	@Test
	public void whenFindById_thenReturnUser() {
		// given
		User user = new User();
		user.setFirstName("User 1");
		entityManager.persist(user);
		entityManager.flush();

		// when
		Optional<User> optUser = userRepository.findById(user.getId());
		User sUser = optUser.get();

		// then
		assertThat(user.getFirstName()).isEqualTo(sUser.getFirstName());
	}

	@Test
	public void saveUser() {
		// given
		User user = new User();
		user.setFirstName("User 1");
		entityManager.persist(user);
		entityManager.flush();

		// when
		User sUser = userRepository.save(user);

		// then
		assertThat(user.getFirstName()).isEqualTo(sUser.getFirstName());

	}

	@Test
	public void deleteUser_ById() {
		// given
		User user = new User();
		user.setFirstName("User 1");
		entityManager.persist(user);
		entityManager.flush();

		// when
		userRepository.deleteById(user.getId());

		// then
		assertThat(userRepository.findById(user.getId())).isEmpty();

	}

	@Test
	public void whenFindAll_thenReturnUserList() {
		// given
		User user = new User();
		user.setFirstName("User 1");
		entityManager.persist(user);
		entityManager.flush();

		User user2 = new User();
		user2.setFirstName("User 2");
		entityManager.persist(user2);
		entityManager.flush();

		// when
		List<User> uList = null;
		uList = userRepository.findAll();

		// then
		assertThat(uList.stream().filter(usr -> "User 1".equalsIgnoreCase(usr.getFirstName())).findFirst().isPresent())
				.isEqualTo(true);
	}

}
