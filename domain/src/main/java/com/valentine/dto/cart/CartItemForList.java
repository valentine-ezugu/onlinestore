package com.valentine.dto.cart;

import com.valentine.domain.Book;
import com.valentine.domain.BookToCartItem;
import com.valentine.domain.Order;
import com.valentine.domain.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;


public class CartItemForList {

    private Long id;
    private int qty;
    private BigDecimal subTotal;
    private Book book;
    private List<BookToCartItem> bookToCartItemList;
    private ShoppingCart shoppingCart;
    private Order order;

    public CartItemForList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<BookToCartItem> getBookToCartItemList() {
        return bookToCartItemList;
    }

    public void setBookToCartItemList(List<BookToCartItem> bookToCartItemList) {
        this.bookToCartItemList = bookToCartItemList;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
