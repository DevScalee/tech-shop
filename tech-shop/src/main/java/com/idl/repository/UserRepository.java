package com.idl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idl.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
