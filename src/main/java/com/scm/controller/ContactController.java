package com.scm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.dto.ContactRequest;
import com.scm.dto.ContactResponse;
import com.scm.services.ContactService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/contacts")
@RequiredArgsConstructor
@Validated
public class ContactController {
	
	private final ContactService contactService;
	
	@PostMapping
	public ResponseEntity<ContactResponse> addContact(
			@Valid @RequestBody ContactRequest contactRequest){
		
		ContactResponse response = contactService.addContact(contactRequest);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
		
		
		
	}
	
	

}
