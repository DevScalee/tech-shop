package com.idl.services;

import com.idl.models.Product;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
public interface ProductService {
	
	Product saveProduct(Product product);
	List<Product> findAllProdcuts();
	Optional<Product> findProduct(Long id ); 
	void deleteProduct(Long id);
	Product editProduct(Long id,Product product) throws Exception;
}
