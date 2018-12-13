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

import com.manage.project.dao.IUserRepository;
import com.manage.project.model.User;
import com.manage.project.service.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	@Mock
	private IUserRepository userRepo;

	@InjectMocks
	private UserServiceImpl userService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testViewsUsers() {
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setFirstName("User 1");
		userList.add(user1);
		User user2 = new User();
		user2.setFirstName("User 2");
		userList.add(user2);

		when(userRepo.findAll()).thenReturn(userList);
		List<User> result = userService.viewUsers();
		assertEquals(2, result.size());
	}

	@Test
	public void testAddUser() {

		User user1 = new User();
		user1.setFirstName("User 1");
		user1.setLastName("UserLast 1");
		user1.setEmpId(1);

		when(userRepo.save(user1)).thenReturn(user1);
		User user = userService.addUser(user1);
		assertEquals("User 1", user.getFirstName());

	}

	@Test
	public void testDeleteUser() {
		User user1 = new User();
		user1.setFirstName("User 1");
		userService.deleteUser(1);
		verify(userRepo, times(1)).deleteById(1);
	}

	@Test
	public void testEditUser() {

		User user1 = new User();
		user1.setFirstName("User 1");

		when(userRepo.save(user1)).thenReturn(user1);
		User user = userService.editUser(user1);
		assertEquals("User 1", user.getFirstName());

	}

	@Test
	public void testFindUserById() {

		User user1 = new User();
		user1.setFirstName("User 1");

		when(userRepo.findById(1)).thenReturn(Optional.of(user1));
		User user = userService.findUserById(1);
		assertEquals("User 1", user.getFirstName());

	}

}
