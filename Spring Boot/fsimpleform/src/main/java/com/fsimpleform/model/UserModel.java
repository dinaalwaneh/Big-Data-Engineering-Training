package com.fsimpleform.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "users_table")

public class UserModel {
	public UserModel() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String login;
	String password;
	String email;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)return true;
		if(o==null)return false;
		UserModel that = (UserModel)o;
		return Objects.equals(id,that.id) && Objects.equals(login,that.login) &&Objects.equals(password,that.password) &&Objects.equals(email,that.email);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id,login,password,email);
	}
	
	@Override
	public String toString() {
		return "UserModel{"+
	"id="+id+", login="+login+", email="+email+"}";
	}
}
