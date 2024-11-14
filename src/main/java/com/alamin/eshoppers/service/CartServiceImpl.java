package com.alamin.eshoppers.service;

import com.alamin.eshoppers.domain.Cart;
import com.alamin.eshoppers.domain.CartItem;
import com.alamin.eshoppers.domain.Product;
import com.alamin.eshoppers.domain.User;
import com.alamin.eshoppers.exceptions.CartItemNotFoundException;
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

        Product product = findProduct(productId);
        addProductToCart(product, cart);
        updateCart(cart);

    }


    private Product findProduct(String productId) {
        if (productId == null || productId.length() == 0) {
            throw new IllegalArgumentException("Product id can not be null!");
        }
        Long id = parseProductId(productId);

       return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found by id : " + id));
    }

    private void updateCart(Cart cart) {

        Integer totalItem = getTotalItem(cart);
        BigDecimal totalPrice = calculatedPrice(cart);

        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);

        cartRepository.update(cart);
    }

    @Override
    public void removeProductFromCart(String productId, Cart cart) {
        Product product = findProduct(productId);
        removeProductFromCart(product, cart);
        updateCart(cart);
    }

    private void removeProductFromCart(Product productToRemove, Cart cart) {
        var itemOptional = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().equals(productToRemove))
                .findAny();

        var cartItem = itemOptional
                .orElseThrow(() -> new CartItemNotFoundException("Cart not found by product:" + productToRemove));

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItem.setPrice(cartItem.getPrice().subtract(productToRemove.getPrice()));
            for (CartItem  c: cart.getCartItems()){
                System.out.println(c.getProduct().getName() + " = " + c.getQuantity());
            }
            cartItemRepository.update(cartItem);
        } else {
            cart.getCartItems().remove(cartItem);
            cartItemRepository.remove(cartItem);
        }
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
