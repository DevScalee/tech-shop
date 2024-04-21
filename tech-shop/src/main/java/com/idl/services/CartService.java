package com.idl.services;

import java.util.List;

import com.idl.models.Cart;

public interface CartService {
    Cart addProductToCart(Long cartId, Long productId, Integer quantity);

    List<Cart> getAllCarts();

    Cart getCart( Long cartId);

    Cart updateProductQuantityInCart(Long cartId, Long productId, Integer quantity);

    void updateProductInCarts(Long cartId, Long productId);

    String deleteProductFromCart(Long cartId, Long productId);
}
