package com.idl.techshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private Long id ;
	
	@NotBlank
	@Size(max = 25)
	private String name ;
	
	@NotBlank
	@Size(max = 25)
	private String lastName ;
	
	@Email
	private String email ;
	
	 @NotBlank
	 @Size(max = 120)
	 private String password;
	  
	  
	  private Boolean accountStatus ;

	  @Column(name="resetToken")
	  private String resetToken;
	  

	  
	@Column(name="confirmToken")
	private String confirmToken;
	
	
	@Enumerated(EnumType.STRING)
	  private Role role ;

}
