package com.scm.services;

import org.springframework.data.domain.Page;

import com.scm.dto.ContactRequest;
import com.scm.dto.ContactResponse;

public interface ContactService {

	ContactResponse addContact(ContactRequest request);
	
	Page<ContactResponse> getUserContacts(String email,int page,int size);
	
	void deleteContact(Long contactId, String email);
}
