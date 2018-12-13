package com.manage.project.service;

import java.util.List;

import com.manage.project.model.User;

public interface IUserService {
	
	public User addUser(User user);
	public User editUser(User user);
	public void deleteUser(int userid);
	public List<User> viewUsers();
	public User findUserById(int userid);

}
