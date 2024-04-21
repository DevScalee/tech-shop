package com.idl.controllers;

import com.idl.models.Cart;
import com.idl.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long cartId,
                                                 @PathVariable Long productId,
                                                 @RequestParam Integer quantity) {
        Cart cart = cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCart(cartId);
        return ResponseEntity.ok().body(cart);
    }

    @PutMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Cart> updateProductQuantityInCart(@PathVariable Long cartId,
                                                            @PathVariable Long productId,
                                                            @RequestParam Integer quantity) {
        Cart cart = cartService.updateProductQuantityInCart(cartId, productId, quantity);
        return ResponseEntity.ok().body(cart);
    }

    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Long cartId,
                                                        @PathVariable Long productId) {
        String message = cartService.deleteProductFromCart(cartId, productId);
        return ResponseEntity.ok().body(message);
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok().body(carts);
    }
}