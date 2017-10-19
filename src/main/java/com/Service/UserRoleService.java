package com.Service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Dao.UserRoleDao;
import com.entity.UserRole;
import com.google.common.collect.Maps;

@Component
public class UserRoleService {

	private static Logger logger = LoggerFactory.getLogger(UserRoleService.class);
	
	private static Map<String, UserRole> userRoleMap = Maps.newLinkedHashMap();
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	public UserRole findByRoleName(String rolename){
		
		 if (userRoleMap.containsKey(rolename)) {
	            return userRoleMap.get(rolename);
	        }
	        else {
	            UserRole userRole = userRoleDao.findOneByName(rolename);
	            if (userRole != null) {
	                userRoleMap.put(rolename, userRole);
	            }
	            return userRole;
	        }
	}
}
