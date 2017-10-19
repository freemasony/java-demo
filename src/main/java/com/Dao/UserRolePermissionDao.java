package com.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.entity.UserRolePermission;

public interface UserRolePermissionDao  extends JpaRepository<UserRolePermission,Integer>,PagingAndSortingRepository<UserRolePermission, Integer>{

	List<UserRolePermission> findByRoleIdAndStatusAndType(Integer roleId, int i, int j);

}
