package com.javaguids.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaguids.model.Location;
import com.javaguids.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User save(User user);
}
