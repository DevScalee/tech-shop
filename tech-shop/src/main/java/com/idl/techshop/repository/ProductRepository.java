package com.idl.techshop.repository;

import com.idl.techshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    public List<Product> findAllByName(String name);

    @Query("SELECT p FROM Product p WHERE p.name = :productName")
    Product findProductWithName(@Param("productName") String gameName);

    Boolean existsByName(String name);

    public Product findProductById(Long id);
}
