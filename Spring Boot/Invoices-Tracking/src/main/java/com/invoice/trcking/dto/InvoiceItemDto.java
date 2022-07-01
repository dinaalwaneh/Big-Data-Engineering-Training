package com.invoice.trcking.dto;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.invoice.trcking.model.InvoiceItemKey;
import com.invoice.trcking.model.Item;

import lombok.Data;

@Data
public class InvoiceItemDto {

    private Long invoiceId;
	private String itemName;
	private int quantity;
	private long amount;
	public InvoiceItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoiceItemDto(Long invoiceId, String itemName, int quantity, long amount) {
		super();
		this.invoiceId = invoiceId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
}
