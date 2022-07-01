package com.invoice.trcking.dto;

import java.time.LocalDateTime;

import com.invoice.trcking.model.User;

public class HistoryDto {

	 private Long id;
	 private Long invoiceId;
     private String userName;
     private LocalDateTime  dateOfUpdate;
     private String  updatedData;

	public HistoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoryDto(Long id, Long invoiceId, String userName, LocalDateTime dateOfUpdate, String updatedData) {
		super();
		this.id = id;
		this.invoiceId = invoiceId;
		this.userName = userName;
		this.dateOfUpdate = dateOfUpdate;
		this.updatedData = updatedData;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDateTime getDateOfUpdate() {
		return dateOfUpdate;
	}

	public void setDateOfUpdate(LocalDateTime dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}

	public String getUpdatedData() {
		return updatedData;
	}

	public void setUpdatedData(String updatedData) {
		this.updatedData = updatedData;
	}
     
}
