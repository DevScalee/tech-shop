package com.idl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idl.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    public List<Product> findAllByName(String name);

    @Query("SELECT p FROM Product p WHERE p.name = :productName")
    Product findProductWithName(@Param("productName") String gameName);

    
    Boolean existsByName(String name);

    public Optional<Product> findProductById(Long id);
   
    
    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(CAST(p.prix AS string)) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(CAST(p.quantityInStock AS string)) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
     List<Product> searchProducts(@Param("searchTerm") String searchTerm);
}
