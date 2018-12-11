package com.manage.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.project.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer>{

}
