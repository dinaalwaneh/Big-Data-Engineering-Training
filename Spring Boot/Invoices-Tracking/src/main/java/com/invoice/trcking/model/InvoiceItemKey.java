package com.invoice.trcking.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InvoiceItemKey implements Serializable{

	    @Column(name = "invoice_id")
	    Long invoiceId;

	    @Column(name = "item_id")
	    Long itemId;

	    
		public InvoiceItemKey() {
			super();
			// TODO Auto-generated constructor stub
		}


		public InvoiceItemKey(Long studentId, Long courseId) {
			super();
			this.invoiceId = studentId;
			this.itemId = courseId;
		}


		public Long getStudentId() {
			return invoiceId;
		}


		public void setStudentId(Long studentId) {
			this.invoiceId = studentId;
		}


		public Long getCourseId() {
			return itemId;
		}


		public void setCourseId(Long courseId) {
			this.itemId = courseId;
		}


		@Override
		public int hashCode() {
			return Objects.hash(itemId, invoiceId);
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			InvoiceItemKey other = (InvoiceItemKey) obj;
			return Objects.equals(itemId, other.itemId) && Objects.equals(invoiceId, other.invoiceId);
		}
}
