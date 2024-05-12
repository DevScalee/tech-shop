package com.idl.services;

import com.idl.models.Category;
import com.idl.models.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
	
	Product saveProduct(Product product);
	List<Product> findAllProdcuts();
	Optional<Product> findProduct(Long id ); 
	void deleteProduct(Long id);
	Product editProduct(Long id,Product product) throws Exception;
	public List<Product> searchProduct(String itemSearch)  throws Exception;
	
	Category saveCategory(Category category);
	List<Category> findAllCategories();
	Optional<Category> findCategory(Long id ); 
	void deleteCategory(Long id);
	Category editCategory(Long id,Category category) throws Exception;

	Product addProduct(Product product);

	public List<Product> getLatestProducts() ;
}
