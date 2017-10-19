package com.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Dao.AccountDao;
import com.Dao.UserPermissionsDao;
import com.Dao.UserRoleDao;
import com.Dao.UserRoleMemberDao;
import com.Dao.UserRolePermissionDao;
import com.entity.Admin;
import com.entity.UserPermissions;
import com.entity.UserRole;
import com.entity.UserRoleMember;
import com.entity.UserRolePermission;
import com.google.common.collect.Maps;

@Component
public class AccountService {
	
	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	
	 
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserRolePermissionService userRolePermissionService;
	
	@Autowired
	private UserPermissionsService userPermissionService;
	
	@Autowired
	private UserRoleMemberDao userRoleMemberDao;
	
	public Admin getAdminUserByLoginName(String username) {
		// TODO Auto-generated method stub
		Admin admin=accountDao.findOneByLoginNameAndState(username,0);
		return admin;
	}

	
	public Set<String> getRoles(Admin admin){
		List<UserRoleMember> list = userRoleMemberDao.findByAdminAndStatus(admin.getId(), 0);

		Set<String> sets = new HashSet<String>();
		for(UserRoleMember role:list){
			 sets.add(role.getUserRole().getName());
		} 
		
		return sets;
		
	}
	
	public Set<String> getPermissions(Set<String> userRoles){
		Set<String> permissionSet = new HashSet<String>();
		
		for(String role:userRoles){
			UserRole userRole=userRoleService.findByRoleName(role);
			getRolePermissions(userRole.getUserRoleId(),permissionSet);
			
		} 
		
		return permissionSet;
	}


	
	private void getRolePermissions(Integer userRoleId,
			Set<String> permissionSet) {
		// TODO Auto-generated method stub
		List<UserRolePermission> userRolePermissionList = userRolePermissionService.getRolePermissionsByRoleIdAndType(userRoleId);
		for(UserRolePermission userRolePermission:userRolePermissionList){
			UserPermissions userPermissions=userPermissionService.getPermissionAdmin(userRolePermission.getPermissionId());
			if(userPermissions!=null){
	               if (!permissionSet.contains(userPermissions.getName())) {
	            	   permissionSet.add(userPermissions.getName());
	               }
	           }
		}
	}
	
	
	
}
