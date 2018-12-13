package com.manage.project.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.project.dao.IUserRepository;
import com.manage.project.model.User;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserRepository userRepo;

	@Override
	@Transactional
	public User addUser(User user) {		
		return userRepo.save(user);
	}

	@Override
	@Transactional
	public User editUser(User user) {
		return userRepo.save(user);
	}

	@Override
	@Transactional
	public void deleteUser(int userid) {
		userRepo.deleteById(userid);		
	}

	@Override
	public List<User> viewUsers() {
		return userRepo.findAll();
	}

	@Override	
	public User findUserById(int userid) {
		Optional<User> optUser = userRepo.findById(userid);
		User user = optUser.isPresent() ? optUser.get() : null;
		return user;
	}


}
