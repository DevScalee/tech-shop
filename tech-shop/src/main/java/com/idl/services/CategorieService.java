package com.idl.services;

import java.util.List;

import java.util.Optional;

import com.idl.models.Categorie;

public interface CategorieService {

	Categorie saveCategorie(Categorie categorie);
	List<Categorie> findAllCategorie();
	Optional<Categorie> findCategorie(Long id ); 
	void deleteCategorie(Long id);
	Categorie editCategorie(Long id,Categorie categorie) throws Exception;
	
}
