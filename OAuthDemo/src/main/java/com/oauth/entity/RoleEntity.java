package com.oauth.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "RoleEntity")
public class RoleEntity implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer rid;
	private String name;
	@ManyToMany(mappedBy = "roleEntity")
	private Set<UserEntity> userEntity;

	public RoleEntity() {

	}

	public RoleEntity(Integer rid, String name, Set<UserEntity> userEntity) {

		this.rid = rid;
		this.name = name;
		this.userEntity = userEntity;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserEntity> getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(Set<UserEntity> userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public String getAuthority() {
		return name;
	}

}