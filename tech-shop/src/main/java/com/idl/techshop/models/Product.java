package com.idl.techshop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Produit")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    private  Product product;

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

}
