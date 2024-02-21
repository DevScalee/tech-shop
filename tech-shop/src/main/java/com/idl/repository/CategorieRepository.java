package com.idl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.idl.models.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {

	 public List<Categorie> findAllByName(String name);

	    
	    Categorie findCategorieWithName(@Param("CategorieName") String gameName);

	    
	    Boolean existsByName(String name);

	    public Optional<Categorie> findCayegorieById(Long id);
	
}