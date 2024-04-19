package com.idl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	  
	  
	  @Query("SELECT u FROM User u WHERE " +
		       "LOWER(u.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
		       "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
		       "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
		       "LOWER(u.address) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
		       "LOWER(u.phoneNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
		List<User> searchUsers(@Param("searchTerm") String searchTerm);

}
