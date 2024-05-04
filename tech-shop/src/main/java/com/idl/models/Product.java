package com.idl.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products")
@Data
@AllArgsConstructor
public class Product {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@NotBlank
	@Size(max = 20)
	private String name;

	@NotBlank
	private String description;

	private int prix;

	@Column(name="quantité en stock")
	private int quantityInStock;


	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "product", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	private List<CartItem> products = new ArrayList<>();
	
	
    
    /*
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageModel> images = new ArrayList<>();
    
    public List<ImageModel> getImages() {
		return images;
	}

	public void setImages(List<ImageModel> images) {
	    this.images.clear();

	    this.images.addAll(images);

	    for (ImageModel image : images) {
	        image.setProduct(this);
	    }
	}


	
	  public void addImage(ImageModel image) {
	        images.add(image);
	        image.setProduct(this);
	    }

	    public void removeImage(ImageModel image) {
	        images.remove(image);
	        image.setProduct(null);
	    }
    */

	public List<CartItem> getProducts() {
		return products;
	}

	public void setProducts(List<CartItem> products) {
		this.products = products;
	}

	@Column(name = "picbyte",length=100000)
	private List<byte[]> img ;

	public List<byte[]> getImg() {
		return img;
	}

	public void setImg(List<byte[]> img) {
		this.img = img;
	}

	@Enumerated(EnumType.STRING)
	private ProductAvailabilityStatus status;

	public void updateProductStatus()
	{
		if(quantityInStock==0)
			setStatus(ProductAvailabilityStatus.OUT_OF_STOCK);
		else if (quantityInStock<=10) {
			setStatus(ProductAvailabilityStatus.CRITICAL);
		}
		else setStatus(ProductAvailabilityStatus.IN_STOCK);
	}

	@SneakyThrows
	public void purchase(int quantity) throws Exception {
		if(quantity<quantityInStock)
			quantityInStock=-quantity;
		else throw new Exception("Not enough quatity availabe");
	}
	public void restock (int quatity)
	{
		quantityInStock=+quatity;
		updateProductStatus();
	}
	public void setStatus(ProductAvailabilityStatus status) {
		this.status = status;
	}

	public Product(Long id, @NotBlank @Size(max = 20) String name, @NotBlank String description, int prix,
				   int quantityInStock, ProductAvailabilityStatus status) {
		this.productId = id;
		this.name = name;
		this.description = description;
		this.prix = prix;
		this.quantityInStock = quantityInStock;
		this.status = status;
	}

	

	public Long getId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}



	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public ProductAvailabilityStatus getStatus() {
		return status;
	}



	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product(Long id, @NotBlank @Size(max = 20) String name, @NotBlank String description, int prix,
				   List<byte[]> images, int quantityInStock, ProductAvailabilityStatus status, Category category) {
		this.productId = id;
		this.name = name;
		this.description = description;
		this.prix = prix;
		this.img = images;
		this.quantityInStock = quantityInStock;
		this.status = status;
		this.category=category;

	}

	public Product(@NotBlank @Size(max = 20) String name, @NotBlank String description, int prix,
				   List<byte[]> images, int quantityInStock, ProductAvailabilityStatus status, Category category) {
		this.name = name;
		this.description = description;
		this.prix = prix;
		this.img = images;
		this.quantityInStock = quantityInStock;
		this.status = status;
		this.category=category;
	}

	public Product(String name, String description, int prix, int quantityInStock, ProductAvailabilityStatus status, Category category) {
		this.name = name;
		this.description = description;
		this.prix = prix;
		this.quantityInStock = quantityInStock;
		this.status = status;
		this.category = category;
	}

	public Product() {

	}


	public Product(Long productId, @NotBlank @Size(max = 20) String name, @NotBlank String description, int prix, int quantityInStock,List<CartItem> products, ProductAvailabilityStatus status) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.prix = prix;
		this.quantityInStock = quantityInStock;
		this.products = products;
		this.status = status;
	}



}