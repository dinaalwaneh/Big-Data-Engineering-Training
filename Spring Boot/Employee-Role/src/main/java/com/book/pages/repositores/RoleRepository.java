package com.book.pages.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.book.pages.model.*;
 
public interface RoleRepository extends CrudRepository<Role, Long> {

	public Role save(Role order);

 
}



 