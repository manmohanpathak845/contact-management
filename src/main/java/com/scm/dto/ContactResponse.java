package com.scm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {
	
	private Long id;
	
	private String name;
		
	private String nickName;
	
	private String company;
	
	private String email;

	private String phone;
	
	private String imageUrl;
	
	private String description;

    private LocalDateTime createdAt;

	

}
