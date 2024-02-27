package com.idl.payload.request;

import com.idl.models.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class SignupRequest {

	  @NotBlank
	  @Size(min = 3, max = 20)
	  private String name;
	  
	  @NotBlank
	  @Size(min = 3, max = 20)
	  private String lastName;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  private String email;

	  
	  @NotBlank
	  @Size(min = 6, max = 40)
	  private String password;
	  
	
	  @NotBlank
	  @Size(min = 3, max = 30)
	  private String address;
	  
	  @NotBlank
	  @Size(min = 3, max = 20)
	  private String phoneNumber;
	  
	  private Role role;

	public String getName() {
		return name;
	}

	public void setName(String firstname) {
		this.name = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastname(String lastname) {
		this.lastName = lastname;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phonenumber) {
		this.phoneNumber = phonenumber;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	 
	}
