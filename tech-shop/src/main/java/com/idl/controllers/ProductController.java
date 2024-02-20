package com.idl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import com.idl.services.ProductService;
import com.idl.models.ImageModel;
import com.idl.models.Product;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	 @Autowired
	    private ProductService productService;

	    @GetMapping("/products")
	    public List<Product> getAllProducts() {
	        return productService.findAllProdcuts();
	    }
	   
	    
	    @PostMapping(value="/addproduct", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	    public Product addProduct(@RequestPart Product product, @RequestPart("images") List<MultipartFile> files) throws IOException {
	        List<byte[]> images = new ArrayList<>();

	       for (MultipartFile file : files) {
	        	images.add(file.getBytes());
	        }
	       product.setImg(images);
	        return productService.saveProduct(product);
	    }
	    
	    @DeleteMapping("/deleteproduct/{id}")
	    public ResponseEntity<Product> deleteProduct(@PathVariable Long id)
	    {
	    	productService.deleteProduct(id);
	    	return new ResponseEntity<>(HttpStatus.OK);
	    }
	    
	    @PutMapping("/updateproduct/{id}")
	    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody Product product)
	    {
	    	 try {
	             Product editedProduct = productService.editProduct(id,product);
	             return ResponseEntity.ok(editedProduct);
	         } catch (Exception e) {
	             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
	         }
	    	
	    }
}
