package com.invoice.trcking.model;


import javax.persistence.*;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Table
@Entity
 
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Enumerated(EnumType.STRING )
    @Column(unique = true,name="role_name")
    private RoleType name;
  
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<User> employees = new HashSet<>();

    
    
    public Role(Long id, RoleType name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleType getName() {
		return name;
	}

	public void setName(RoleType name) {
		this.name = name;
	}

	public Set<User> getPages() {
		return employees;
	}

	public void setPages(Set<User> employees) {
		this.employees = employees;
	}

	public Role() {
    }

    public Role(RoleType name) {
        this.name = name;
   
    }

}