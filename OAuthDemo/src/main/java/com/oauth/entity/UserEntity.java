package com.oauth.entity;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "UserEntity")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer uid;

	private String name;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "uid") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "rid") })
	private Set<RoleEntity> roleEntity;

	public UserEntity() {

	}

	public UserEntity(Integer uid, String name, String password, Set<RoleEntity> roleEntity) {
		this.uid = uid;
		this.name = name;
		this.password = password;
		this.roleEntity = roleEntity;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleEntity> getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(Set<RoleEntity> roleEntity) {
		this.roleEntity = roleEntity;
	}

}
