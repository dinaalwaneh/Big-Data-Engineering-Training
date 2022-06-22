package com.book.pages.model;

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
    private RoleName name;
  
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();

    
    
    public Role(Long id, RoleName name) {
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

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}

	public Set<Employee> getPages() {
		return employees;
	}

	public void setPages(Set<Employee> employees) {
		this.employees = employees;
	}

	public Role() {
    }

    public Role(RoleName name) {
        this.name = name;
   
    }

}