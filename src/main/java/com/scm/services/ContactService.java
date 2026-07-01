package com.scm.services;

import com.scm.dto.ContactRequest;
import com.scm.dto.ContactResponse;

public interface ContactService {

	ContactResponse addContact(ContactRequest request);
}
