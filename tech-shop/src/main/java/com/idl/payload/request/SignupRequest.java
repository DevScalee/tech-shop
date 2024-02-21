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
	  private String firstname;
	  
	  @NotBlank
	  @Size(min = 3, max = 20)
	  private String lastname;

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
	  private String phonenumber;
	  
	  private Role role;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	 
	}
