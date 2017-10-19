package com.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_role_member")
public class UserRoleMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2393407966209738339L;
	
	@Id
	@Column(name="user_role_member_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long userRoleMemberId;
	
	@Column(name="user_id")
	private Long userId;
	
 
	 @OneToOne(optional = false, cascade = CascadeType.REFRESH)
	 @JoinColumn(name = "user_role_id", referencedColumnName = "user_role_id")
	 private UserRole userRole; 
	
	@Column(name="type")
	private Integer type;
	
	@Column(name="status")
	private Integer status;

	public Long getUserRoleMemberId() {
		return userRoleMemberId;
	}

	public void setUserRoleMemberId(Long userRoleMemberId) {
		this.userRoleMemberId = userRoleMemberId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userRole == null) ? 0 : userRole.hashCode());
		result = prime
				* result
				+ ((userRoleMemberId == null) ? 0 : userRoleMemberId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoleMember other = (UserRoleMember) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		if (userRoleMemberId == null) {
			if (other.userRoleMemberId != null)
				return false;
		} else if (!userRoleMemberId.equals(other.userRoleMemberId))
			return false;
		return true;
	}
 
	 
}
