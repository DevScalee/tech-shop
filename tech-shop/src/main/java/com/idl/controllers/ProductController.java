package com.idl.controllers;

import com.idl.models.User;
import com.idl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.idl.models.Category;
import com.idl.models.Product;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
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

	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product addedProduct = productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
	}

	    @DeleteMapping("/deleteproduct/{id}")
	    public ResponseEntity<Product> deleteProduct(@PathVariable Long id)
	    {
	    	productService.deleteProduct(id);
	    	return new ResponseEntity<>(HttpStatus.OK);
	    }

	@GetMapping("/findproduct/{id}")
	public Optional<Product> findProduct (@PathVariable Long id )

	{
		return productRepository.findProductById(id);
	}



	@PutMapping("/updateproduct/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id,
										   @RequestPart Product product,
										   @RequestPart("images") List<MultipartFile> files) {
		try {
			List<byte[]> images = new ArrayList<>();
			for (MultipartFile file : files) {
				images.add(file.getBytes());
			}
			product.setImg(images);

			Product editedProduct = productService.editProduct(id, product);
			return ResponseEntity.ok(editedProduct);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
	}
	    
	    @GetMapping("/product/search")
		public ResponseEntity<?> searProducts(@RequestParam String searchTerm) {
		    try {
		        List<Product> products = productService.searchProduct(searchTerm);
		        return ResponseEntity.ok(products);
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No matching result!");
		    }
		}




	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = productService.findAllCategories();
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long categoryId) {
		Optional<Category> category = productService.findCategory(categoryId);
		return ResponseEntity.ok(category);
	}

	@PostMapping("/addcategory")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		Category createdCategory = productService.saveCategory(category);

		return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId,
												   @RequestBody Category category) throws Exception {
		Category updatedCategory = productService.editCategory(categoryId, category);
		return ResponseEntity.ok(updatedCategory);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
		productService.deleteCategory(categoryId);
		return ResponseEntity.ok("Category with ID " + categoryId + " has been deleted successfully.");
	}

	@GetMapping("/product/latest")
	public ResponseEntity<List<Product>> getLatestProducts() {
		List<Product> latestProducts = productRepository.findTop6ByOrderByIdDesc();
		return new ResponseEntity<>(latestProducts, HttpStatus.OK);
	}



}
