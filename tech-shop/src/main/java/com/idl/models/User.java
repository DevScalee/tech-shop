package com.idl.models;

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
@Table(name="users")
public class User {
	
	public User(Long id, @NotBlank @Size(max = 25) String name, @NotBlank @Size(max = 25) String lastName,
			@Email String email, @NotBlank @Size(max = 120) String password, Boolean accountStatus, String resetToken,
			String confirmToken, Role role) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.accountStatus = accountStatus;
		this.resetToken = resetToken;
		this.confirmToken = confirmToken;
		this.role = role;
	}
	


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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getAccountStatus() {
		return accountStatus;
	}


	public void setAccountStatus(Boolean accountStatus) {
		this.accountStatus = accountStatus;
	}


	public String getResetToken() {
		return resetToken;
	}


	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}


	public String getConfirmToken() {
		return confirmToken;
	}


	public void setConfirmToken(String confirmToken) {
		this.confirmToken = confirmToken;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public User() {
	
	}

	
	
}