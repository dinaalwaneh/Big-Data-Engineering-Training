package com.invoice.trcking.model;


import javax.persistence.*;


import java.io.Serializable;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	@Column(name = "date_of_create")
    private LocalDateTime  dateOfCreate;
	@Column(name = "date_of_update")
    private LocalDateTime  dateOfUpdate;
    @Column(name = "number", unique = true, nullable = false)
    private long  number;
    @Column(name = "total_amount")
    private long totalAmount;
    @Column(name = "paid_amount")
    private long totalPaid;
    @Column(name = "remaining_amount")
    private long remainingAmount;
    @Column(name = "status")
    private String status; //paid or not paid
    @Column(name = "is_deleted")
    private String isDeleated;
    @Column(name = "fileName")
    private String fileName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" )
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",nullable = true  )
    private Customer customer;
    
   
    @OneToMany(mappedBy = "invoice")
    Set<InvoiceItem> items;

    //Empty constructor
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Args constructer
	public Invoice(Long id, LocalDateTime formattedDate, LocalDateTime formattedDate2, long number, long totalAmount,
			long totalPaid, long remainingAmount, String status, String isDeleated,Customer customer ,User user, String fileName) {
		super();
		this.id = id;
		this.dateOfCreate = formattedDate;
		this.dateOfUpdate = formattedDate2;
		this.number = number;
		this.totalAmount = totalAmount;
		this.totalPaid = totalPaid;
		this.remainingAmount = remainingAmount;
		this.status = status;
		this.isDeleated = isDeleated;
		this.user = user;
		this.customer = customer;
		this.fileName = fileName;
	}

	//setters & getters :
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateOfCreate() {
		return dateOfCreate;
	}

	public void setDateOfCreate(LocalDateTime dateOfCreate) {
		this.dateOfCreate = dateOfCreate;
	}

	public LocalDateTime getDateOfUpdate() {
		return dateOfUpdate;
	}

	public void setDateOfUpdate(LocalDateTime dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(long totalPaid) {
		this.totalPaid = totalPaid;
	}

	public long getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(long remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsDeleated() {
		return isDeleated;
	}

	public void setIsDeleated(String isDeleated) {
		this.isDeleated = isDeleated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(Set<InvoiceItem> items) {
		this.items = items;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}