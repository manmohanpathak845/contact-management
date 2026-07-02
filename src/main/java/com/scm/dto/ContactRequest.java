package com.scm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactRequest {

	@NotBlank(message="Name is required")
	private String name;
	
	private String nickName;
	
	private String comapany;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid Email")
	private String email;
	
	
	@NotBlank(message = "Phone No. is required")
	@Pattern(regexp = "^[0-9]{10}$", message = 	"phone No. must contain exactly 10 digits")
	private String phone;
	
	private String imageUrl;
	
	@Size(max=500)
	private String description;
	
}
