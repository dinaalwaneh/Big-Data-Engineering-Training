package com.javaguids.dto;

import lombok.Data;

@Data
public class UserLocationDTO {

	private long userId;
	private String email;
	private String place;
	private String longitude;
	private String latitude;
	
}
