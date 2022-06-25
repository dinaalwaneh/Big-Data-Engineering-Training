package com.invoice.trcking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private String  name;
    private String  discription;
    private long ammount;
    private long quantity;
    private boolean isDeleated;
    
    //Empty Constructor
	public ItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Args Constructor
	public ItemDto(Long id, String name, String discription, long ammount, long quantity, boolean isDeleated) {
		super();
		this.id = id;
		this.name = name;
		this.discription = discription;
		this.ammount = ammount;
		this.quantity = quantity;
		this.isDeleated = isDeleated;
	}

	//setters & getters :
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

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
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

	public boolean isDeleated() {
		return isDeleated;
	}

	public void setDeleated(boolean isDeleated) {
		this.isDeleated = isDeleated;
	}

}
