package com.javaguids.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaguids.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
