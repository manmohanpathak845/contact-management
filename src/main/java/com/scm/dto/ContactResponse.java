package com.scm.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactResponse {
	
	private Long id;
	
	private String name;
		
	private String nickName;
	
	private String comapany;
	
	private String email;

	private String phone;
	
	private String imageUrl;
	
	private String description;

    private LocalDateTime createdAt;

	

}
