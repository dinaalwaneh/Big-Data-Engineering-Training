package com.invoice.trcking.dto;

import java.security.Timestamp;

import javax.persistence.Column;

import com.invoice.trcking.model.Customer;
import com.invoice.trcking.model.User;

import lombok.Data;

@Data
public class InvoiceDto {
 
    private Long id;
    private Timestamp  dateOfCreate;
    private Timestamp  dateOfUpdate;
    private long  number;
    private long totalAmount;
    private long totalPaid;
    private long remainingAmount;
    private String status;
    private String isDeleated;
    private String customerName;
    private String userName;
    private Customer customer;
    private User user;
    
    //Empty constructer
	public InvoiceDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Args constructer
	public InvoiceDto(Long id, Timestamp dateOfCreate, Timestamp dateOfUpdate, long number, long totalAmount,
			long totalPaid, long remainingAmount, String status, String isDeleated, String customerName,
			String userName, Customer customer, User user) {
		super();
		this.id = id;
		this.dateOfCreate = dateOfCreate;
		this.dateOfUpdate = dateOfUpdate;
		this.number = number;
		this.totalAmount = totalAmount;
		this.totalPaid = totalPaid;
		this.remainingAmount = remainingAmount;
		this.status = status;
		this.isDeleated = isDeleated;
		this.customerName = customerName;
		this.userName = userName;
		this.customer = customer;
		this.user = user;
	}

	//setters & Getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateOfCreate() {
		return dateOfCreate;
	}

	public void setDateOfCreate(Timestamp dateOfCreate) {
		this.dateOfCreate = dateOfCreate;
	}

	public Timestamp getDateOfUpdate() {
		return dateOfUpdate;
	}

	public void setDateOfUpdate(Timestamp dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(long totalPaid) {
		this.totalPaid = totalPaid;
	}

	public long getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(long remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsDeleated() {
		return isDeleated;
	}

	public void setIsDeleated(String isDeleated) {
		this.isDeleated = isDeleated;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
