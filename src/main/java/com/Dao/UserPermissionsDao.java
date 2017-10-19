package com.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.entity.UserPermissions;

public interface UserPermissionsDao  extends JpaRepository<UserPermissions,Integer>,PagingAndSortingRepository<UserPermissions, Integer>{

	UserPermissions findByIdAndType(Integer id, int i);

}
