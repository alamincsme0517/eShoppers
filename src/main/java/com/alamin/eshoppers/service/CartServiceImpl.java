package com.alamin.eshoppers.service;

import com.alamin.eshoppers.domain.Cart;
import com.alamin.eshoppers.domain.CartItem;
import com.alamin.eshoppers.domain.Product;
import com.alamin.eshoppers.domain.User;
import com.alamin.eshoppers.exceptions.ProductNotFoundException;
import com.alamin.eshoppers.repository.CartItemRepository;
import com.alamin.eshoppers.repository.CartRepository;
import com.alamin.eshoppers.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    private final ProductRepository productRepository ;
    private final CartItemRepository cartItemRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public Cart getCartByUser(User currentUser) {

        Optional<Cart> optionalCart = cartRepository.findByUser(currentUser);

        return optionalCart.
                orElseGet(() -> createNewCart(currentUser));
    }


    private Cart createNewCart(User currentUser) {
        Cart cart = new Cart();
        cart.setUser(currentUser);
        return cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(String productId, Cart cart) {
        if (productId == null || productId.length() == 0) {
            throw new IllegalArgumentException("Product id can not be null!");
        }
        Long id = parseProductId(productId);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found by id : " + id));

        addProductToCart(product, cart);

        Integer totalItem = getTotalItem(cart);
        BigDecimal totalPrice = calculatedPrice(cart);

        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);

        cartRepository.save(cart);

    }

    private BigDecimal calculatedPrice(Cart cart) {
        return  cart.getCartItems()
                .stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private Integer getTotalItem(Cart cart) {
        return cart.getCartItems()
                .stream()
                .map(CartItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    private void addProductToCart(Product product, Cart cart) {
        var cartItemOptional = findSimilarProductInCart(cart, product);
        var cartItem = cartItemOptional
                .map(this::IncreaseQuantityByOne)
                .orElseGet(() ->
                        CreateNewShoppingCartItem(product));

        cart.getCartItems().add(cartItem);
    }

    private CartItem CreateNewShoppingCartItem(Product product) {
        var cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setPrice(product.getPrice());

        return cartItemRepository.save(cartItem);
    }

    private CartItem IncreaseQuantityByOne(CartItem cartItem) {
        cartItem.setQuantity(cartItem.getQuantity() + 1);

        BigDecimal totalPrice = cartItem
                .getProduct()
                .getPrice()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        cartItem.setPrice(totalPrice);

        return cartItemRepository.update(cartItem);

    }

    private Optional<CartItem> findSimilarProductInCart(Cart cart, Product product) {
        return cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().equals(product)).findFirst();
    }

    private Long parseProductId(String productId) {
        try {
            return Long.parseLong(productId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Product id must be a number", e);
        }
    }


}
