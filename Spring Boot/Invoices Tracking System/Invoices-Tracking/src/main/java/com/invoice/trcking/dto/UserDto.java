package com.invoice.trcking.dto;

import lombok.Data;

@Data
public class UserDto {

	    private Long id;
	    private String  name;
	    private String  username;
	    private String password;
	    private String enabled;
	    
		public UserDto() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserDto(Long id, String name, String username, String password, String enabled) {
			super();
			this.id = id;
			this.name = name;
			this.username = username;
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

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEnabled() {
			return enabled;
		}

		public void setEnabled(String enabled) {
			this.enabled = enabled;
		}
	    
}
