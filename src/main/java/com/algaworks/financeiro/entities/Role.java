package com.algaworks.financeiro.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;

@Entity
@Table(name="roles")
@Controller
@Scope("request")
public class Role implements GrantedAuthority{
	private static final long serialVersionUID = -3968396919486158590L;

	private Long id;
	private String description;

	@Id
	@GeneratedValue
	@Column(name="role_id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="role_description", unique=true)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	@Transient
	public String getAuthority() {
		return description;
	}
}
