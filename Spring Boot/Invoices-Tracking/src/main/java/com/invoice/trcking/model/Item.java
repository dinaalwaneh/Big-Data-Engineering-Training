package com.invoice.trcking.model;

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
	@Column(name = "id")
    private Long id;
    @Column(unique = true , name = "name")
    private String  name;
    @Column(name = "discription")
    private String  discription;
    @Column(name = "ammount")
    private long ammount;
    @Column(name = "quantity")
    private long quantity;
    @Column(name = "is_deleated")
    private boolean isDeleated;
    
    @OneToMany(mappedBy = "item")
    Set<InvoiceItem> invoiceItems = new HashSet<InvoiceItem>();

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Item(Long id, String name, String discription, long ammount, long quantity, Boolean isDeleated) {
		super();
		this.id = id;
		this.name = name;
		this.discription = discription;
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

	public boolean getIsDeleated() {
		return isDeleated;
	}

	public void setIsDeleated(Boolean isDeleated) {
		this.isDeleated = isDeleated;
	}


	public String getDiscription() {
		return discription;
	}


	public void setDiscription(String discription) {
		this.discription = discription;
	}


	public Set<InvoiceItem> getItems() {
		return invoiceItems;
	}


	public void setItems(Set<InvoiceItem> items) {
		this.invoiceItems = items;
	}
    
    
}
