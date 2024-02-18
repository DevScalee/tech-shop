package com.idl.services;

import com.idl.models.Product;
import java.util.List;
public interface ProductService {
	
	Product saveProduct(Product product);
	List<Product> findAllProdcuts();
	void deleteProduct(Long id);
	void editProduct(Product product);
}
