package com.idl.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idl.models.Category;
import com.idl.models.Product;
import com.idl.repository.CategoryRepository;
import com.idl.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepo;
	@Autowired
	CategoryRepository categoryRepo;
	
	@Transactional
	public Product saveProduct(Product product) {
		Product newProduct = new Product (
				product.getId(),
				product.getName(),
				product.getDescription(),
				product.getPrix(),
				product.getImg(),
				product.getQuantityInStock(),
				product.getStatus(),
				product.getCategory());
		return productRepo.save(newProduct);
				
	}

	@Override
	public List<Product> findAllProdcuts() {
		return productRepo.findAll();
	}
	
	@Override
	public void deleteProduct(Long id) {
		 productRepo.deleteById(id);
		
	}

	@Override
	public Product editProduct(Long id , Product product) throws Exception {
		Optional<Product> opProduct = productRepo.findProductById(id);
		if (opProduct.isPresent())
		{
			Product currentProduct = opProduct.get();
			currentProduct.setName(product.getName());
			currentProduct.setDescription(product.getDescription());
			currentProduct.setPrix(product.getPrix());
			currentProduct.setQuantityInStock(product.getQuantityInStock());
			currentProduct.setStatus(product.getStatus());
			currentProduct.setCategory(product.getCategory());
			return currentProduct;
		}else throw new Exception();
		
	}

	@Override
	public Optional<Product> findProduct(Long id) {
		return productRepo.findProductById(id);
	}

	@Override
	public List<Product> searchProduct(String itemSearch) throws Exception {
		List<Product> searchedProducts = productRepo.searchProducts(itemSearch);
	    if (!searchedProducts.isEmpty()) {
	        return searchedProducts;
	    } else {
	        throw new Exception();
	    }
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public Optional<Category> findCategory(Long id) {
		return categoryRepo.findById(id);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepo.deleteById(id);
	}

	@Override
	public Category editCategory(Long id, Category category) throws Exception {
		Optional<Category> opCategory = categoryRepo.findById(id);
		if(!opCategory.isEmpty())
		{
			Category currentCategory = opCategory.get();
			currentCategory.setName(category.getName());
			return currentCategory;
		}else throw new Exception();
	}

}
