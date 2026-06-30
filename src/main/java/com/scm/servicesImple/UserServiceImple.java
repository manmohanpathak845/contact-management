package com.scm.servicesImple;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.dto.RegisterRequest;
import com.scm.entity.User;
import com.scm.enums.Role;
import com.scm.repository.UserRepository;
import com.scm.services.UserService;


@Service
public class UserServiceImple implements UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	public UserServiceImple(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void register(RegisterRequest registerRequest) {
		// TODO Auto-generated method stub
		
		
		if(userRepository.existsByEmail(registerRequest.getEmail())) {
			throw new RuntimeException("Email already registered");
			
		}
		
		User user = new User();
		
		user.setUserName(registerRequest.getUserName());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setAbout(registerRequest.getAbout());
		
		
		user.setProfilePhotoUrl(null);
		user.setRole(Role.USER);
		user.setEnabled(true);
		
		userRepository.save(user);
		
	}

}
