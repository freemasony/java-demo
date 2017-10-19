package com.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.entity.Admin;

 

public interface AccountDao extends JpaRepository<Admin,Long>,PagingAndSortingRepository<Admin, Long>{

	Admin findOneByLoginNameAndState(String username, int state);
}
