package com.manage.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.project.model.Project;

@Repository
public interface IProjectRepository extends JpaRepository<Project,Integer>{

}
