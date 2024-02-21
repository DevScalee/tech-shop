package com.idl.controllers;

import com.idl.models.Categorie;

import com.idl.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @PostMapping("/addCategorie")
    public ResponseEntity<Categorie> saveCategorie(@RequestBody Categorie categorie) {
        Categorie savedCategorie = categorieService.saveCategorie(categorie);
        return new ResponseEntity<>(savedCategorie, HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Categorie>> findAllCategories() {
        List<Categorie> categories = categorieService.findAllCategorie();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("getCategorie/{id}")
    public ResponseEntity<Categorie> findCategorieById(@PathVariable Long id) {
        Optional<Categorie> categorie = categorieService.findCategorie(id);
        return categorie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("deleteCategorie/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("updateCategorie/{id}")
    public ResponseEntity<Categorie> editCategorie(@PathVariable Long id, @RequestBody Categorie categorie) {
        try {
            Categorie editedCategorie = categorieService.editCategorie(id, categorie);
            return new ResponseEntity<>(editedCategorie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}