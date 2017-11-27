package com.bookstore.dto.shoppingCart;

import com.bookstore.domain.CartItem;
import com.bookstore.domain.ShoppingCart;
import com.bookstore.domain.User;

import java.math.BigDecimal;
import java.util.List;


public class ShoppingCartLite {

    private Long id;
    private BigDecimal grandTotal;
    private List<CartItem> cartItemList;
    private User user;

    public ShoppingCartLite() {
    }

    public ShoppingCartLite(ShoppingCart shoppingCart) {
        id = shoppingCart.getId();
        grandTotal = shoppingCart.getGrandTotal();
        cartItemList = shoppingCart.getCartItemList();
        user = shoppingCart.getUser();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
