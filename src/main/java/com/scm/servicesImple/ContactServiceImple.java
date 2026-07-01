package com.scm.servicesImple;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.scm.dto.ContactRequest;
import com.scm.dto.ContactResponse;
import com.scm.entity.Contact;
import com.scm.entity.User;
import com.scm.repository.ContactRepository;
import com.scm.repository.UserRepository;
import com.scm.services.ContactService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImple implements ContactService {
	
	
	private final ContactRepository contactRepository;
	private final UserRepository userRepository;

	@Override
	public ContactResponse addContact(ContactRequest request) {
		
		Authentication authentication=
				SecurityContextHolder.getContext().getAuthentication();
		
		String email = authentication.getName();
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(()->new RuntimeException("User not found"));
		
		Contact contact = new Contact();
		
		contact.setName(request.getName());
		contact.setNickName(request.getEmail());
		contact.setCompany(request.getComapany());
		contact.setEmail(request.getEmail());
		contact.setPhone(request.getPhone());
		contact.setImageUrl(request.getImageUrl());
		contact.setDescription(request.getDescription());
		
		contact.setUser(user);
		
		Contact savedContact = contactRepository.save(contact);
				
		return mapToResponse(savedContact);
	}

	public ContactResponse mapToResponse(Contact contact) {
		
		return ContactResponse.builder()
				.id(contact.getId())
				.name(contact.getName())
				.nickName(contact.getNickName())
				.comapany(contact.getCompany())
				.email(contact.getEmail())
				.phone(contact.getPhone())
				.imageUrl(contact.getImageUrl())
				.description(contact.getDescription())
				.createdAt(contact.getCreatedAt())
				.build();
	}
	
}
