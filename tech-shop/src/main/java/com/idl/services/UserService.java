package com.idl.services;

import java.util.List;

import com.idl.models.User;
import com.idl.models.Userr;


public interface UserService {
	void deleteUser(Long id) ;


	Userr saveUser(Userr user);

	List<Userr> findAllUsers();

	Userr updateUser(Userr user);

	
}
