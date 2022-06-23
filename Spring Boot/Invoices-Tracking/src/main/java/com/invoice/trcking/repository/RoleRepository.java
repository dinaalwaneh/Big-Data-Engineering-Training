package com.invoice.trcking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import  com.invoice.trcking.model.*;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	public Role save(Role order);

 
}



 