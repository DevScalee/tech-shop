package com.idl.services;

import java.util.List;

import com.idl.models.User;


public interface UserService {
	User saveUser(User user);

	List<User> findAllUsers();

}
