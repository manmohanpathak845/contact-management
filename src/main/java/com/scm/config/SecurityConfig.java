package com.scm.config;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.scm.servicesImple.CustomeUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
		private final  JwtAuthenticationFilter filter;
	
		private final CustomeUserDetailsService service;
		
		

	public SecurityConfig(
			JwtAuthenticationFilter filter,
			CustomeUserDetailsService service) {
			this.filter = filter;
			this.service = service;
		}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(service);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(
			AuthenticationConfiguration configuration) throws Exception {
		
		return configuration.getAuthenticationManager();
	}


	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception{
		http
			.cors(Customizer.withDefaults())
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth->auth
					.requestMatchers("/api/auth/**").permitAll()
					.requestMatchers(org.springframework.http.HttpMethod.OPTIONS,"/**").permitAll()
					.anyRequest().authenticated()
					)
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
			
		
		return http.build();
	}
	
	

}
