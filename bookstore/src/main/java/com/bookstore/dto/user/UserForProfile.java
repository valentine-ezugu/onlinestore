package com.bookstore.dto.user;

import com.bookstore.domain.*;
import com.bookstore.domain.security.UserRole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserForProfile {

    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;
    private String phone;
    private boolean enabled = true;
    private ShoppingCart shoppingCart;
    private List<UserShipping> userShippingList;

    private List<UserPayment> userPaymentList;
    private Set<UserRole> userRoles = new HashSet<>();
    private List<Order> orderList;

    public UserForProfile(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        firstName = user.getFirstname();
        lastName = user.getLastname();
        email = user.getEmail();
        phone = user.getPhone();
        enabled = user.isEnabled();
        shoppingCart = user.getShoppingCart();
        userShippingList = user.getUserShippingList();
        userRoles = user.getUserRoles();
        userPaymentList = user.getUserPaymentList();
        orderList = user.getOrderList();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<UserShipping> getUserShippingList() {
        return userShippingList;
    }

    public void setUserShippingList(List<UserShipping> userShippingList) {
        this.userShippingList = userShippingList;
    }

    public List<UserPayment> getUserPaymentList() {
        return userPaymentList;
    }

    public void setUserPaymentList(List<UserPayment> userPaymentList) {
        this.userPaymentList = userPaymentList;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
