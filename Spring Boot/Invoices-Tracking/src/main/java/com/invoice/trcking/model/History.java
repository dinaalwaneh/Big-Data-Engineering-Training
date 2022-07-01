package com.invoice.trcking.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.bytebuddy.asm.Advice.This;

@Entity
@Table(name = "history")
public class History {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id" , nullable = false )
    private Invoice invoice;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Employee_id" , nullable = true )
    private User user;
    
    @Column(name = "date_of_update")
    private LocalDateTime  dateOfUpdate;
    
    @Column(name = "json_of_update")
    private String  updatedData;

	public History() {
		super();
		// TODO Auto-generated constructor stub
	}

	public History(Long id, Invoice invoice, User user, LocalDateTime dateOfUpdate, String updatedData) {
		super();
		this.id = id;
		this.invoice = invoice;
		this.user = user;
		this.dateOfUpdate = dateOfUpdate;
		this.updatedData = updatedData;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
