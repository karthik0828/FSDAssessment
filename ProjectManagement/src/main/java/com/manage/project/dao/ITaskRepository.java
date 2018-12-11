package com.manage.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.project.model.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task,Integer>{

}
