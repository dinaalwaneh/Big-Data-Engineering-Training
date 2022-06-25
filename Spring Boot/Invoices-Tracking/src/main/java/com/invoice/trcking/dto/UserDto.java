package com.invoice.trcking.dto;

import lombok.Data;

@Data
public class UserDto {

	  
	    private Long id;
	    private String  name;
	    private String  userName;
	    private String  address;
	    private String  phone;
	    private String  email;
	    private String password;
	    private boolean enabled;
	    
		public UserDto() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserDto(Long id, String name, String userName, String address, String phone, String email,
				String password, boolean enabled) {
			super();
			this.id = id;
			this.name = name;
			this.userName = userName;
			this.address = address;
			this.phone = phone;
			this.email = email;
			this.password = password;
			this.enabled = enabled;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		
}
