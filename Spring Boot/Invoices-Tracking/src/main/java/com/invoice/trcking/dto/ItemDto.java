package com.invoice.trcking.dto;

import javax.persistence.Column;

import lombok.Data;
@Data
public class ItemDto {

	private Long id;  
	@Column(nullable = false)
    private String  name;
    private long ammount;
    private long quantity;
    private String isDeleated;
	public ItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemDto(Long id, String name, long ammount, long quantity, String isDeleated) {
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
