package com.idl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idl.models.Categorie;
import com.idl.repository.CategorieRepository;

import jakarta.transaction.Transactional;

@Service
public class CategorieServiceImpl implements CategorieService {


	@Autowired
    private CategorieRepository categorieRepository;

	@Transactional
    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> findAllCategorie() {
        return categorieRepository.findAll();
    }

    @Override
    public Optional<Categorie> findCategorie(Long id) {
        return categorieRepository.findById(id);
    }

    @Override
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public Categorie editCategorie(Long id, Categorie categorie) throws Exception {
        Optional<Categorie> existingCategorie = categorieRepository.findById(id);

        if (existingCategorie.isPresent()) {
        	Categorie updatedCategorie = existingCategorie.get();
            updatedCategorie.setName(categorie.getName());

            // Vous pouvez également mettre à jour d'autres champs ici si nécessaire.

            return categorieRepository.save(updatedCategorie);
        } else {
            throw new Exception("La catégorie avec l'ID " + id + " n'a pas été trouvée.");
        }
    }
}