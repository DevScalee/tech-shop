package com.idl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idl.models.Role;
import com.idl.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	  Optional<User> findByName(String username);
	  Optional<User> findByRole(Role role );
	  Optional<User> findByEmail(String email);
   	Optional<User> findByResetToken(String resetToken);
	 Optional<User> findByConfirmToken(String confirmToken);

	  Boolean existsByEmail(String email);
	  
	  
	  
}
