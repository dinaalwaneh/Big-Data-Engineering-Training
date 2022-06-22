package com.invoice.trcking.dto;

import com.invoice.trcking.model.RoleType;

public class RoleDto {
	
	public Long id; 
    private RoleType name;
    
	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleDto(Long id, RoleType name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleType getName() {
		return name;
	}

	public void setName(RoleType name) {
		this.name = name;
	}
    
	
    
}
