package com.idl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idl.models.Product;
import com.idl.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepo;
	
	@Transactional
	public Product saveProduct(Product product) {
		Product newProduct = new Product (
				product.getId(),
				product.getName(),
				product.getDescription(),
				product.getPrix(),
				product.getImg(),
				product.getQuantityInStock(),
				product.getStatus());

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
	 public Product editProduct(Long id, Product product) throws Exception {
        Optional<Product> opProduct = productRepo.findProductById(id);
        if (opProduct.isPresent()) {
            Product currentProduct = opProduct.get();
            currentProduct.setName(product.getName());
            currentProduct.setDescription(product.getDescription());
            currentProduct.setPrix(product.getPrix());
            if (product.getImg() != null && !product.getImg().isEmpty()) {
                currentProduct.setImg(product.getImg());
            }
            currentProduct.setQuantityInStock(product.getQuantityInStock());
            currentProduct.setStatus(product.getStatus());
            return productRepo.save(currentProduct); // Assuming you have a save method in your repository
        } else {
            throw new Exception("Product not found");
        }
    }

	@Override
	public Optional<Product> findProduct(Long id) {
		return productRepo.findProductById(id);
	}

}
