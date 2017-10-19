package com.Service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Dao.UserPermissionsDao;
import com.entity.UserPermissions;
import com.google.common.collect.Maps;

@Component
public class UserPermissionsService {

	private static Logger logger = LoggerFactory.getLogger(UserPermissionsService.class);
	
    private Map<Integer, UserPermissions> permissionMap = Maps.newLinkedHashMap();
	  
    @Autowired
    private UserPermissionsDao userPermissionsDao;
	  
	  public UserPermissions getPermissionAdmin(Integer id) {

	        if (permissionMap.containsKey(id)) {
	            return permissionMap.get(id);
	        }
	        else {
	        	UserPermissions permission = userPermissionsDao.findByIdAndType(id,0);
	            if (permission != null) {
	            	permissionMap.put(permission.getId(), permission);
	            }
	            return permission;
	        }
	    }
}
