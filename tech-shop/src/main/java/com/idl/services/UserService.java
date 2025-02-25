package com.idl.services;

import java.util.List;
import java.util.Optional;

import com.idl.models.User;


public interface UserService {
	
	User saveUser(User user);

	List<User> findAllUsers();
	public Optional<User> findUserByEmail(String email);
    public Optional<User> findUserByResetToken(String resetToken);
     
    void deleteUser(Long id);
        
    Optional<User> getUserById(Long id);
	User updateUser(User user, Long id  ) throws Exception;

	
	public List<User> searchUser(String itemSearch)  throws Exception;


	String forgotPassword(String email);

	public String resetPassword(String token, String password);
}
