package com.invoice.trcking.dto;

import lombok.Data;

@Data
public class CustomerDto {

	  
    private Long id;
    private String  name;
    private String  customerName;
    private String  address;
    private String  phone;
    private String  email;
    private boolean enabled;
    
	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDto(Long id, String name, String customerName, String address, String phone, String email,
			 boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.customerName = customerName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.enabled = enabled;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
