package com.invoice.trcking.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table
public class InvoiceItem {

	    @Id
	    @EmbeddedId
	    private InvoiceItemKey id;

	    @ManyToOne
	    @MapsId("invoiceId")
	    @JoinColumn(name = "invoice_id")
	    private Invoice invoice;

	    @ManyToOne
	    @MapsId("itemId")
	    @JoinColumn(name = "item_id")
	    private Item item;

	    @Column(name = "Quantity")
	    int quantity;
	    
	    @Column(name = "Amount")
	    long amount;

		public InvoiceItem() {
			super();
			// TODO Auto-generated constructor stub
		}

		public InvoiceItem(InvoiceItemKey id, Invoice invoice, Item item, int quantity, long amount) {
			super();
			this.id = id;
			this.invoice = invoice;
			this.item = item;
			this.quantity = quantity;
			this.amount = amount;
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

		public long getAmount() {
			return amount;
		}

		public void setAmount(long amount) {
			this.amount = amount;
		}
	     
}
