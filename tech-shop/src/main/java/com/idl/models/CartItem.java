package com.idl.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

	@JsonBackReference
    @ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
    @NotNull
    @Column(nullable = false)
    private int quantity;

	public Long getId() {
		return cartItemId;
	}

	public void setId(Long id) {
		this.cartItemId = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
