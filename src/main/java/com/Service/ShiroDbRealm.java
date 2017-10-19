package com.Service;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.entity.Admin;
import com.entity.ShiroUser;


public class ShiroDbRealm extends AuthorizingRealm {

	 
	private AccountService accountService;
	
	 
	
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		  ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		  Admin admin=accountService.getAdminUserByLoginName(shiroUser.getLoginName());

		  //��ȡ�û���ɫ
		  Set<String> roles = accountService.getRoles(admin);
		  
		  //��ȡ�û�Ȩ��
		  Set<String> authoritySet = accountService.getPermissions(roles);
		  
		  
	      SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	      info.setRoles(roles);
	      info.setStringPermissions(authoritySet);
	      
		  return info;
	}

//	  /**
//     * ��֤�ص�����,��¼ʱ����.
//     */
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authcToken) throws AuthenticationException {
//		 
//		 UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
//		 
//		 Admin admin=accountService.getAdminUserByLoginName(token.getUsername());
//		 
//		 ShiroUser shiroUser = new ShiroUser(admin.getId(), admin.getLoginName(), admin.getName());
//		  String password = MD5.md5(String.valueOf(token.getPassword())).toLowerCase();
//          token.setPassword(password.toCharArray());
//          System.out.println("pwd:" + password);
//          System.out.println("old:" + admin.getPassword());
//          return new SimpleAuthenticationInfo(shiroUser, admin.getPassword().toLowerCase(), getName());
//		  
//	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	 

	
}
