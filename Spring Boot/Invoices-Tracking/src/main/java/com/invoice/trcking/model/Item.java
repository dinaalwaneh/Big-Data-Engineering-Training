package com.invoice.trcking.model;

import java.security.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(unique = true)
    private String  name;
    private long ammount;
    private long quantity;
    @Column(name = "isDeleated")
    private String isDeleated;
    
    @OneToMany(mappedBy = "item")
    Set<InvoiceItem> items = new HashSet<InvoiceItem>();

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

 

	public Item(Long id, String name, long ammount, long quantity, String isDeleated) {
		super();
		this.id = id;
		this.name = name;
		this.ammount = ammount;
		this.quantity = quantity;
		this.isDeleated = isDeleated;
		 
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

	public long getAmmount() {
		return ammount;
	}

	public void setAmmount(long ammount) {
		this.ammount = ammount;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getIsDeleated() {
		return isDeleated;
	}

	public void setIsDeleated(String isDeleated) {
		this.isDeleated = isDeleated;
	}
    
    
}
