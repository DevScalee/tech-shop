package com.idl.services;

import com.idl.Exceptions.APIException;
import com.idl.Exceptions.ResourceNotFoundException;
import com.idl.models.Cart;
import com.idl.models.CartItem;
import com.idl.models.Product;
import com.idl.repository.CartItemRepository;
import com.idl.repository.CartRepository;
import com.idl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    public Cart addProductToCart(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem != null) {
            // Update quantity of existing item
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepo.save(cartItem);
        } else {
            // Create new cart item
            if (product.getQuantityInStock() < quantity) {
                throw new APIException("Please, make an order of the " + product.getName()
                        + " less than or equal to the quantity " + product.getQuantityInStock() + ".");
            }

            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setCart(cart);
            newCartItem.setQuantity(quantity);
            cartItemRepo.save(newCartItem);
        }

        // Update total price of the cart
        cart.setTotalPrice(cart.getTotalPrice() + (product.getPrix() * quantity));

        return cart;
    }

    @Override
    public List<Cart> getAllCarts() {
        List<Cart> carts = cartRepo.findAll();

        if (carts.isEmpty()) {
            throw new APIException("No cart exists");
        }

        return carts;
    }

    @Override
    public Optional<Cart> getCart(Long cartId) {
        Optional<Cart> cart = cartRepo.findById(cartId);

        if (cart == null) {
            throw new ResourceNotFoundException("Cart", "cartId", cartId);
        }

        return cart;
    }

    @Override
    public Cart updateProductQuantityInCart(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        if (product.getQuantityInStock() == 0) {
            throw new APIException(product.getName() + " is not available");
        }

        if (product.getQuantityInStock() < quantity) {
            throw new APIException("Please, make an order of the " + product.getName()
                    + " less than or equal to the quantity " + product.getQuantityInStock() + ".");
        }

        CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new APIException("Product " + product.getName() + " not available in the cart!!!");
        }

        double cartPrice = cart.getTotalPrice() - (cartItem.getProduct().getPrix() * cartItem.getQuantity());

        product.setQuantityInStock(product.getQuantityInStock() + cartItem.getQuantity() - quantity);

        // cartItem.setProductPrice(product.getSpecialPrice());
        cartItem.setQuantity(quantity);
        // cartItem.setDiscount(product.getDiscount());

        cart.setTotalPrice(cartPrice + (cartItem.getProduct().getPrix() * quantity));

        cartItem = cartItemRepo.save(cartItem);

        return cart;
    }

    @Override
    public void updateProductInCarts(Long cartId, Long productId) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new APIException("Product " + product.getName() + " not available in the cart!!!");
        }

        double cartPrice = cart.getTotalPrice() - (cartItem.getProduct().getPrix() * cartItem.getQuantity());

        //  cartItem.setProductPrice(product.getSpecialPrice());

        cart.setTotalPrice(cartPrice + (cartItem.getProduct().getPrix() * cartItem.getQuantity()));

        cartItem = cartItemRepo.save(cartItem);
    }

    public String deleteProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new ResourceNotFoundException("Product", "productId", productId);
        }

        cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getProduct().getPrix() * cartItem.getQuantity()));

        Product product = cartItem.getProduct();
        product.setQuantityInStock(product.getQuantityInStock() + cartItem.getQuantity());

        cartItemRepo.deleteCartItemByProductIdAndCartId(cartId, productId);

        return "Product " + cartItem.getProduct().getName() + " removed from the cart !!!";
    }
}