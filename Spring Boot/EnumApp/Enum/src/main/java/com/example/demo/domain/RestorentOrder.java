package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity
@Data
public class RestorentOrder{

	
	@Enumerated(EnumType.STRING )
	private OrderStatus status;
	
	private String dish;

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getDish() {
		return dish;
	}

	public void setDish(String dish) {
		this.dish = dish;
	}

}


