package com.invoice.trcking.model;

import javax.persistence.*;


import java.io.Serializable;

@Entity
@Table(name = "employee")
public class User implements Serializable {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String  name;
    @Column(name = "user_name" , unique = true , nullable = false)
    private String  username; //userName
    @Column(name = "address")
    private String  address;
    @Column(name = "phone_number", unique = true , nullable = false)
    private String  phone;
    @Column(name = "email", unique = true , nullable = false)
    private String  email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id" , nullable = false )
    private Role role;

    
    //PS :  constructers , setters and getters i can replace them with annotations from lombok dependancy ->
    //@Data -> for setters and getters 
    //@ArgsConstructer & NoArgsConstructer 
    //but it doesn't work with meee :)
    //empty constructer :
    public User() {
    	
    }

    //args constructer :
	public User(Long id, String name, String userName, String address, String phone, String email, String password,
			boolean isEnabled, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.username = userName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.isEnabled = isEnabled;
		this.role = role;
	}

    //setters and getters
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


	public String getUserName() {
		return username;
	}


	public void setUserName(String userName) {
		this.username = userName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return isEnabled;
	}


	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}



	
 }
