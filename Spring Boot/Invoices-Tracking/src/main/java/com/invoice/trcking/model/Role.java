package com.invoice.trcking.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity(name = "role")
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Long id;
    @Enumerated(EnumType.STRING )
    @Column(name="role_Type", unique = true)
    private RoleType name;
  
    //mappedBy = "role" -> role is the object in user entity :
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<User> employees = new HashSet<>();

    
    //PS :  constructers , setters and getters i can replace them with annotations from lombok dependancy ->
    //@Data -> for setters and getters 
    //@ArgsConstructer & NoArgsConstructer 
    //but it doesn't work with meee :)
    //args constructer :
    public Role(Long id, RoleType name) {
		super();
		this.id = id;
		this.name = name;
	}	
    
    //setters and getters
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