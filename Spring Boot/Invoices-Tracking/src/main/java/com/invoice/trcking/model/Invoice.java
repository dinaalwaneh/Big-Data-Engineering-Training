package com.invoice.trcking.model;


import javax.persistence.*;


import java.io.Serializable;
import java.security.Timestamp;
import java.util.Set;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp  dateOfCreat;
    private Timestamp  dateOfUpdate;
    @Column(unique = true)
    private String  number;
    private String ammount;
    private String isDeleated;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id" , nullable = false )
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id" , nullable = true )
    private Customer customer;
    
   
    @OneToMany(mappedBy = "invoice")
    Set<InvoiceItem> items;


	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoice(Long id, Timestamp dateOfCreat, Timestamp dateOfUpdate, String number, String ammount,
			String isDeleated, User user, Customer customer) {
		super();
		this.id = id;
		this.dateOfCreat = dateOfCreat;
		this.dateOfUpdate = dateOfUpdate;
		this.number = number;
		this.ammount = ammount;
		this.isDeleated = isDeleated;
		this.user = user;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateOfCreat() {
		return dateOfCreat;
	}

	public void setDateOfCreat(Timestamp dateOfCreat) {
		this.dateOfCreat = dateOfCreat;
	}

	public Timestamp getDateOfUpdate() {
		return dateOfUpdate;
	}

	public void setDateOfUpdate(Timestamp dateOfUpdate) {
		this.dateOfUpdate = dateOfUpdate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAmmount() {
		return ammount;
	}

	public void setAmmount(String ammount) {
		this.ammount = ammount;
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

    
}
