package com.scm.servicesImple;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.entity.User;
import com.scm.repository.UserRepository;


@Service
public class CustomeUserDetailsService implements UserDetailsService {
	
	private final UserRepository repository;
	
	

	public CustomeUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}


	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user= repository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User Not found"));
		// TODO Auto-generated method stub
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRole().name())
				.build();
	}	

}
