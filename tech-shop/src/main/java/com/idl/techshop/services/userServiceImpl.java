package com.idl.techshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idl.techshop.repository.UserRepository;

@Service
public class userServiceImpl implements userService {

	@Autowired
	UserRepository userRepo;
}
