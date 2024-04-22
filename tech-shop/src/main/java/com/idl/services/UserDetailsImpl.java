package com.idl.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idl.models.Cart;
import com.idl.models.CartItem;
import com.idl.models.User;
import com.idl.repository.UserRepository;
@Service
public class UserDetailsImpl implements UserService,UserDetails {

	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

	@Autowired
	  private UserRepository userRepository;
	  
	
	@Autowired
	private CartService cartService;
	
	  @Autowired
	  PasswordEncoder encoder;
	
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
		Cart cart = new Cart();
		//user.setCart(cart);
		cart.setUser(user);
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
		Optional<User> user = userRepository.findById(id);
		 if (user.isPresent()) {
		        User existingUser = user.get();
//		        List<CartItem> cartItems = existingUser.getCart().getCartItems();
//				Long cartId = existingUser.getCart().getId();
//
//				cartItems.forEach(item -> {
//
//					Long productId = item.getProduct().getId();
//
//					cartService.deleteProductFromCart(cartId, productId);
//				});
				
				userRepository.delete(existingUser);
		 }

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
	        existingUser.setPassword(encoder.encode(user.getPassword())); 
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
	
	@Override
	public List<User> searchUser(String itemSearch) throws Exception {
	    List<User> searchedUsers = userRepository.searchUsers(itemSearch);
	    if (!searchedUsers.isEmpty()) {
	        return searchedUsers;
	    } else {
	        throw new Exception();
	    }
	}

	public String forgotPassword(String email) {

		Optional<User> userOptional = userRepository.findByEmail(email);

		if (userOptional.isEmpty()) {
			return "Invalid email id.";
		}

		User user = userOptional.get();
		user.setToken(generateToken());
		user.setTokenCreationDate(LocalDateTime.now());

		user = userRepository.save(user);

		return user.getToken();
	}


	public String resetPassword(String token, String password) {

		Optional<User> userOptional = Optional
				.ofNullable(userRepository.findByToken(token));
		if (!userOptional.isPresent()) {
			return "Invalid token.";
		}

		LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

		if (isTokenExpired(tokenCreationDate)) {
			return "Token expired.";

		}

		User user = userOptional.get();
		user.setPassword(encoder.encode(password));
		user.setToken(null);
		user.setTokenCreationDate(null);

		userRepository.save(user);

		return "Your password successfully updated.";
	}


	private String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}


	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}


}

