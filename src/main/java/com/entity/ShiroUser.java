package com.entity;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Created by Cougar on 14-11-15.
 */
public class ShiroUser implements Serializable
{

	private static final long serialVersionUID = -1373760761780840081L;
	public Long id;
	public String loginName;
	public String name;

	public ShiroUser() {
	}

	public ShiroUser(Long id, String loginName, String name)
	{
		this.id = id;
		this.loginName = loginName;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getLoginName()
	{
		return loginName;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString()
	{
		return loginName;
	}

	/**
	 * 重载hashCode,只计算loginName;
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode(loginName);
	}

	/**
	 * 重载equals,只计算loginName;
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		ShiroUser other = (ShiroUser) obj;
		if (loginName == null)
		{
			if (other.loginName != null)
			{
				return false;
			}
		} else if (!loginName.equals(other.loginName))
		{
			return false;
		}
		return true;
	}
}
