package com.fsimpleform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fsimpleform.model.UserModel;

public interface UsersRepository extends JpaRepository<UserModel, Integer>{

	Optional<UserModel> findByLoginAndPassword(String login,String password);
}
