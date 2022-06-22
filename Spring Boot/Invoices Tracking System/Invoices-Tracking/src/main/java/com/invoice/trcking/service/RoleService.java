package com.invoice.trcking.service;

import java.util.List;

import com.invoice.trcking.model.Role;
import com.invoice.trcking.model.User;

public interface RoleService {
	
	public abstract Role createRole(Role role);
	public abstract List<Role> getRoles();
}
