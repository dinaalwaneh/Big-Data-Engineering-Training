package com.invoice.trcking.dto;

import java.security.Timestamp;

import javax.persistence.Column;

import lombok.Data;

@Data
public class InvoiceDto {

	private Long id;
    private Timestamp  dateOfCreat;
    private Timestamp  dateOfUpdate;
    private String  number;
    private String ammount;
    private String isDeleated;
    private String customerName;
    private String userName;
    
	public InvoiceDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoiceDto(Long id, Timestamp dateOfCreat, Timestamp dateOfUpdate, String number, String ammount,
			String isDeleated, String customerName, String userName) {
		super();
		this.id = id;
		this.dateOfCreat = dateOfCreat;
		this.dateOfUpdate = dateOfUpdate;
		this.number = number;
		this.ammount = ammount;
		this.isDeleated = isDeleated;
		this.customerName = customerName;
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateOfCreat() {
		return dateOfCreat;
	}

	public void setDateOfCreat(Timestamp dateOfCreat) {
		this.dateOfCreat = dateOfCreat;
	}

	public Timestamp getDateOfUpdate() {
		return dateOfUpdate;
	}

	public void setDateOfUpdate(Timestamp dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAmmount() {
		return ammount;
	}

	public void setAmmount(String ammount) {
		this.ammount = ammount;
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
    
    
}
