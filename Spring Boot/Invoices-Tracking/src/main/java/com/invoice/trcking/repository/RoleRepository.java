package com.invoice.trcking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import  com.invoice.trcking.model.*;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	public Role save(Role order);
	public Role findByName(RoleType name); 

 
}



 