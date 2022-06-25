package com.invoice.trcking.model;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

@Entity
@Table(name = "customer")
@AllArgsConstructor
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String  name;
    @Column(name = "custumer_name" , unique = true , nullable = false)
    private String  customerName;
    @Column(name = "address")
    private String  address;
    @Column(name = "phone_number", unique = true , nullable = false)
    private String  phone;
    @Column(name = "email", unique = true , nullable = false)
    private String  email;
    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;
	   
    @OneToMany(mappedBy = "customer")
    Set<Invoice> invoices;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String name, String customerName, String address, String phone, String email,
			boolean isEnabled) {
		super();
		this.id = id;
		this.name = name;
		this.customerName = customerName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.isEnabled = isEnabled;
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
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}
}