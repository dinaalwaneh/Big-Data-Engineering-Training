package com.invoice.trcking.dto;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.invoice.trcking.model.Invoice;
import com.invoice.trcking.model.InvoiceItemKey;
import com.invoice.trcking.model.Item;

import lombok.Data;

@Data
public class InvoiceItemDto {

    InvoiceItemKey id;
    Invoice invoice;
    Item item;
    int quantity;
	public InvoiceItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InvoiceItemDto(InvoiceItemKey id, Invoice invoice, Item item, int quantity) {
		super();
		this.id = id;
		this.invoice = invoice;
		this.item = item;
		this.quantity = quantity;
	}
	public InvoiceItemKey getId() {
		return id;
	}
	public void setId(InvoiceItemKey id) {
		this.id = id;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
