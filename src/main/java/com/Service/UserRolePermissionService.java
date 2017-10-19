package com.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Dao.UserRolePermissionDao;
import com.entity.UserRolePermission;

@Component
public class UserRolePermissionService {

	private static Logger logger = LoggerFactory.getLogger(UserRolePermissionService.class);
	
	private static Map<Integer,List<UserRolePermission>> rolePermissionMap = new HashMap<Integer, List<UserRolePermission>>();
	
	
	@Autowired
	private UserRolePermissionDao userRolePermissionDao;
	
	
	public List<UserRolePermission> getRolePermissionsByRoleIdAndType(Integer roleId) {
		 List<UserRolePermission>  list;
	        if(rolePermissionMap.containsKey(roleId)){
	            list = rolePermissionMap.get(roleId);
	        }
	        else{
	            list = userRolePermissionDao.findByRoleIdAndStatusAndType(roleId, 0,0);
	            if(list != null && !list.isEmpty()){
	                rolePermissionMap.put(roleId,list);
	            }
	        }
	        return list;
	}
}
