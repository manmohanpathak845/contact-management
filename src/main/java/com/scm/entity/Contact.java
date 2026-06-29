package com.scm.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "contact_name",nullable = false, length = 100)
	@NotBlank
	private String name;
	
	@Column(length = 100)
	private String nickName;
		
	private String company;
	
	@Column(nullable = false)
	@NotBlank
	@Email
	private String email;
	
	@Column(nullable = false, length = 10)
	@NotBlank
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone Number must contain exactly 10 digits")
	private String phone;
	
	@Column(length = 10000)
	private String imageUrl;
	
	@Column(length = 500)
	@Size(max = 500)
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable = false)
	private User user;

	@CreationTimestamp
	private LocalDateTime createdAt;
	
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
