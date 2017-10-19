package com.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.entity.UserRoleMember;

 

public interface UserRoleMemberDao extends JpaRepository<UserRoleMember,Long>,PagingAndSortingRepository<UserRoleMember, Long>{

	@Query("select u from UserRoleMember u where u.userId=:userId and u.status=:status ")
	List<UserRoleMember> findByAdminAndStatus(@Param("userId") Long userId, @Param("status") Integer status);
	 
}
