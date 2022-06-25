package com.invoice.trcking.model;

import javax.persistence.*;


import java.io.Serializable;

@Entity
@Table(name = "employee")
public class User implements Serializable {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  name;
    private String  username;
    private String password;
    private String enabled;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id" , nullable = false )
    private Role role;


    public User(Long id, String name, String username, String password, String enabled, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}


	public User() {
    	
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEnabled() {
		return enabled;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	
 }
