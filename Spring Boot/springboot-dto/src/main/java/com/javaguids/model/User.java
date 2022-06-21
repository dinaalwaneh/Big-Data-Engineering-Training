package com.javaguids.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		private String email;
		
		@Column(name="first_name")
		private String firstName;
		
		@Column(name="last_name")
		private String lastName;
		
		private String password;
		
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		@JoinColumn(name = "location_id")
		private Location location;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

		 
}
