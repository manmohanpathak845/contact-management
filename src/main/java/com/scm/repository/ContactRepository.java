package com.scm.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entity.Contact;
import com.scm.entity.User;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
	
	Page<Contact> findByUser(User user,Pageable pageable);
	
	Page<Contact> findByUserAndNameContainingIgnoreCase(User user, String name,Pageable pageable);
	
	Page<Contact> findByUserAndEmailContainingIgnoreCase(User user, String email,Pageable pageable);
	
}

