package com.idl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idl.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category findByName(String categoryName);

}
