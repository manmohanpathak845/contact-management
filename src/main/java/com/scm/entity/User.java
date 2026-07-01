package com.scm.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.scm.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users") 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long  userid;
	
	@Column(name = "user_name" , nullable = false)
	@NotBlank(message = "User Name required")
	private String userName;
	
	@Column(name = "email" , unique = true, nullable = false)
	@Email
	@NotBlank
	private String email;
	
	@Column(name= "photo_url")
	private String profilePhotoUrl;
	
	@Size(max=500)
	@Column(name = "about", length =  500)
	private String about;
	
	@NotBlank
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;
	
	
	private boolean enabled = true;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Contact> contacts = new ArrayList<>();
	
	

}
