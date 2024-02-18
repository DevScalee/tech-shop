package com.idl.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Produit")
@Data
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    private String description;

    private int prix;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageModel> images = new ArrayList<>();

  

    @Column(name="quantité en stock")
    private int quantityInStock;

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
		this.id = id;
		this.name = name;
		this.description = description;
		this.prix = prix;
		this.quantityInStock = quantityInStock;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<ImageModel> getImages() {
		return images;
	}

	public void setImages(List<ImageModel> images) {
		this.images = images;
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

	public Product(Long id, @NotBlank @Size(max = 20) String name, @NotBlank String description, int prix,
			List<ImageModel> images, int quantityInStock, ProductAvailabilityStatus status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.prix = prix;
		this.images = images;
		this.quantityInStock = quantityInStock;
		this.status = status;
	}
	public Product() {
	
	}
	
	
}