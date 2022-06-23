package com.invoice.trcking.model;

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
	    InvoiceItemKey id;

	    @ManyToOne
	    @MapsId("invoiceId")
	    @JoinColumn(name = "invoice_id")
	    Invoice invoice;

	    @ManyToOne
	    @MapsId("itemId")
	    @JoinColumn(name = "item_id")
	    Item item;

	    int quantity;

		public InvoiceItem() {
			super();
			// TODO Auto-generated constructor stub
		}

		public InvoiceItem(InvoiceItemKey id, Invoice invoice, Item item, int quantity) {
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
