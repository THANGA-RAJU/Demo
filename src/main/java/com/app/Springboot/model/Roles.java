package com.app.Springboot.model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
    @Enumerated(EnumType.STRING)
	private ConstantRole roleName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ConstantRole getRoleName() {
		return roleName;
	}

	public void setRoleName(ConstantRole roleName) {
		this.roleName = roleName;
	}

	public Roles(int id, ConstantRole roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	public Roles() {
		super();
	}

}
