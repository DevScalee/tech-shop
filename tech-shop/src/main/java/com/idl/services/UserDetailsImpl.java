package com.idl.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idl.models.User;
import com.idl.repository.UserRepository;
@Service
public class UserDetailsImpl implements UserService,UserDetails {
	
	  @Autowired
	  private UserRepository userRepository;
	  
	  private static final long serialVersionUID = 1L;

	  private Long id;
	  
	  private String username;
	  private String name;
	  
	  private String lastName ;

	  private String email;

	  @JsonIgnore
	  private String password;
	  
	  private String address;
	  
	  
	  private String phoneNumber;

	  private Collection<? extends GrantedAuthority> authorities;
	  
	  private Boolean enabled ; 
	  

	  public UserDetailsImpl(Long id, String firstname, String lastname, String email, String password,String phoneNumber,
			  String address , Boolean enabled,
	      Collection<? extends GrantedAuthority> authorities) {
	    this.id = id;
	    this.username = email;
	    this.name = firstname;
	    this.lastName=lastname;
	    this.email = email;
	    this.password = password;
	    this.address = address;
	    this.phoneNumber=phoneNumber;
	    this.enabled = enabled;
	    this.authorities = authorities;
	   
	  }
	  public UserDetailsImpl()
	  {}

	  public static UserDetailsImpl build(User user) {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));

	    return new UserDetailsImpl(
	    		user.getId(),
	    		user.getName(),
	    		user.getLastName(),
	    		user.getEmail(),
	    		user.getPassword(),
	    		user.getPhoneNumber(),
	    		user.getAddress(),
	    		user.getAccountStatus(),
	    		authorities
	       );
	        
	  }


	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	  }

	  public Long getId() {
	    return id;
	  }

	 

	  @Override
	  public String getPassword() {
	    return password;
	  }

	  

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }
	  
	  
	  
	  public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	  public boolean isEnabled() {
	    return true;
	  } 
	  

	  @Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    UserDetailsImpl user = (UserDetailsImpl) o;
	    return Objects.equals(id, user.id);
	  }
	  
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	@Override
	public Optional<User> findUserByResetToken(String resetToken) {
		return userRepository.findByResetToken(resetToken);
	}
	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);	
	}
	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}
	
	@Override
	public User updateUser(Long id,User user) throws Exception {
	    Optional<User> existingUserOptional = userRepository.findById(id);

	    if (existingUserOptional.isPresent()) {
	        User existingUser = existingUserOptional.get();

	        existingUser.setName(user.getName());
	        existingUser.setLastName(user.getLastName());
	        existingUser.setEmail(user.getEmail());
	        existingUser.setPassword(user.getPassword()); 
	        existingUser.setPhoneNumber(user.getPhoneNumber());
	        existingUser.setAddress(user.getAddress());

	      return  userRepository.save(existingUser);
	    } else throw new Exception();
	    
	}
	  
	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	
	}

