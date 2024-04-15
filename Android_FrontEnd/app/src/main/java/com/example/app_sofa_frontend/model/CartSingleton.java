package com.example.app_sofa_frontend.model;

import java.util.ArrayList;
import java.util.List;

public class CartSingleton {

    private static CartSingleton instance;
    private List<ProductResponse> cartItems;

    private CartSingleton() {
        cartItems = new ArrayList<>();
    }

    public static CartSingleton getInstance() {
        if (instance == null) {
            instance = new CartSingleton();
        }
        return instance;
    }

    public List<ProductResponse> getCartItems() {
        return cartItems;
    }

    public void addToCart(ProductResponse product) {
        cartItems.add(product);
    }

    public void clearCart() {
        cartItems.clear();
    }

}
