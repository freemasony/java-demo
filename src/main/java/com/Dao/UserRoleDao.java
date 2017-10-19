package com.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.entity.UserRole;

public interface UserRoleDao  extends JpaRepository<UserRole,Integer>,PagingAndSortingRepository<UserRole, Integer>{

	UserRole findOneByName(String rolename);
		
}
