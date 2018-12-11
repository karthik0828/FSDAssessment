package com.manage.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.project.model.ParentTask;

@Repository
public interface IParentRepository extends JpaRepository<ParentTask,Integer>{

}
