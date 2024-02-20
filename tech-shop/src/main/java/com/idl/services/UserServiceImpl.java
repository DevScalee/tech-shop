package com.idl.services;

import java.util.List;

import com.idl.models.User;
import com.idl.models.Userr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.idl.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;
	

	@Override
	public Userr saveUser(Userr user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	public List<Userr> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public Userr updateUser(Userr user) {
			return userRepo.save(user);

	}

	public void deleteUser(Long id){
		userRepo.deleteById(id);
	}



}
