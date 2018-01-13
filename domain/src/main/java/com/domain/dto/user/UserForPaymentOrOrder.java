package com.domain.dto.user;

import java.util.List;
import com.domain.domain.*;
public class UserForPaymentOrOrder {

    private Long id;
    private String userBillingName;
    private String userBillingStreet1;
    private String userBillingStreet2;
    private String userBillingCity;
    private String userBillingState;
    private String userBillingCountry;
    private String userBillingZipCode;
    private UserPayment userPayment;

    private List<UserShipping> userShippingList;
    private List<UserPayment> userPaymentList;
    private List<Order> orderList;

    public UserForPaymentOrOrder(UserBilling userBilling) {
        id = userBilling.getId();
        userBillingName = userBilling.getUserBillingName();
        userBillingStreet1 = userBilling.getUserBillingStreet1();
        userBillingStreet2 = userBilling.getUserBillingStreet2();
        userBillingCity = userBilling.getUserBillingCity();
        userBillingState = userBilling.getUserBillingState();
        userBillingCountry = userBilling.getUserBillingCountry();
        userBillingZipCode = userBilling.getUserBillingZipCode();
    }

    public UserForPaymentOrOrder(User user) {
        userPaymentList = user.getUserPaymentList();
        orderList = user.getOrderList();
        userShippingList = user.getUserShippingList();
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

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserBillingName() {
        return userBillingName;
    }

    public void setUserBillingName(String userBillingName) {
        this.userBillingName = userBillingName;
    }

    public String getUserBillingStreet1() {
        return userBillingStreet1;
    }

    public void setUserBillingStreet1(String userBillingStreet1) {
        this.userBillingStreet1 = userBillingStreet1;
    }

    public String getUserBillingStreet2() {
        return userBillingStreet2;
    }

    public void setUserBillingStreet2(String userBillingStreet2) {
        this.userBillingStreet2 = userBillingStreet2;
    }

    public String getUserBillingCity() {
        return userBillingCity;
    }

    public void setUserBillingCity(String userBillingCity) {
        this.userBillingCity = userBillingCity;
    }

    public String getUserBillingState() {
        return userBillingState;
    }

    public void setUserBillingState(String userBillingState) {
        this.userBillingState = userBillingState;
    }

    public String getUserBillingCountry() {
        return userBillingCountry;
    }

    public void setUserBillingCountry(String userBillingCountry) {
        this.userBillingCountry = userBillingCountry;
    }

    public String getUserBillingZipCode() {
        return userBillingZipCode;
    }

    public void setUserBillingZipCode(String userBillingZipCode) {
        this.userBillingZipCode = userBillingZipCode;
    }

    public UserPayment getUserPayment() {
        return userPayment;
    }

    public void setUserPayment(UserPayment userPayment) {
        this.userPayment = userPayment;
    }
}
