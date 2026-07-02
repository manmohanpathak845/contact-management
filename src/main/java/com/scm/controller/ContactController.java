package com.scm.controller;

import com.scm.repository.ContactRepository;
import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	private final ContactRepository contactRepository;
	private final ContactService contactService;

	
	
	@PostMapping
	public ResponseEntity<ContactResponse> addContact(
			@Valid @RequestBody ContactRequest contactRequest){
		
		ContactResponse response = contactService.addContact(contactRequest);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
		
	}
	
	@GetMapping
	public ResponseEntity<Page<ContactResponse>> getContacts(
			Principal principal,
			@RequestParam(defaultValue = "0")  int page ,
			@RequestParam(defaultValue = "10") int size
			){
		
		return ResponseEntity.ok(contactService.getUserContacts(principal.getName(), page, size));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteContact(@PathVariable Long id,Principal principal){
		contactService.deleteContact(id, principal.getName());
		
		return ResponseEntity.ok("Contact deleted successfully");
	}
	
	
	
	
	
	

}
