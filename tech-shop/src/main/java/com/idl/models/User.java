package com.idl.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Table(name="users")
public class User {
	
	public User(Long id, @NotBlank @Size(max = 25) String name, @NotBlank @Size(max = 25) String lastName,
			@Email String email, @NotBlank @Size(max = 120) String password,String phoneNumber,String address , Boolean accountStatus, String resetToken,
			String confirmToken, Role role) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber=phoneNumber;
		this.address=address;
		this.accountStatus = accountStatus;
		this.resetToken = resetToken;
		this.confirmToken = confirmToken;
		this.role = role;
	}

	public User( @NotBlank @Size(max = 25) String name, @NotBlank @Size(max = 25) String lastName,
			@Email String email, @NotBlank @Size(max = 120) String password,String phoneNumber,String address , Boolean accountStatus, 
			 Role role) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber=phoneNumber;
		this.address=address;
		this.accountStatus = accountStatus;
		this.role = role;
	}
	public User( Long id, @NotBlank @Size(max = 25) String name, @NotBlank @Size(max = 25) String lastName,
			@Email String email, @NotBlank @Size(max = 120) String password,String phoneNumber,String address , Boolean accountStatus, 
			 Role role) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber=phoneNumber;
		this.address=address;
		this.accountStatus = accountStatus;
		this.role = role;
	}
	
	public User() {
		
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
	  
	 @NotBlank
	 private String phoneNumber;

	 private String token;

	 
	 
	 
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime tokenCreationDate;
	 
	 
	@OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
	private Cart cart;
	
	
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getPhoneNumber() {
			return phoneNumber;
		}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAddress() {
		return address;
	}
	public String getUsername() {
		return email;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	@NotBlank
	 private String address;
	  
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


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getTokenCreationDate() {
		return tokenCreationDate;
	}

	public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
		this.tokenCreationDate = tokenCreationDate;
	}


	public User(Long id, String name, String lastName, String email, String password, String phoneNumber, String token, LocalDateTime tokenCreationDate, String address, Boolean accountStatus, Role role) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.token = token;
		this.tokenCreationDate = tokenCreationDate;
		this.address = address;
		this.accountStatus = accountStatus;
		this.role = role;
	}
}