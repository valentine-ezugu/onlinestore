package com.bookstore.dto.user;


import com.bookstore.domain.User;
import com.bookstore.domain.UserShipping;

public class UserForShippingLite {

    private  Long id;
    private String userShippingName;
    private String userShippingStreet1;
    private String userShippingStreet2;
    private String userShippingCity;
    private String userShippingState;
    private String userShippingCountry;
    private String userShippingZipCode;
    private boolean userShippingDefault;
    private User user;

    public UserForShippingLite() {
    }

    public UserForShippingLite(UserShipping userShipping) {
         id = userShipping.getId();
         userShippingName = userShipping.getUserShippingName();
         userShippingStreet1 = userShipping.getUserShippingStreet1();
         userShippingStreet2 = userShipping.getUserShippingStreet2();
         userShippingCity = userShipping.getUserShippingCity();
         userShippingState = userShipping.getUserShippingState();
         userShippingCountry = userShipping.getUserShippingCountry();
         userShippingZipCode = userShipping.getUserShippingZipCode();
         userShippingDefault = userShipping.isUserShippingDefault();
         user = userShipping.getUser();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserShippingName() {
        return userShippingName;
    }

    public void setUserShippingName(String userShippingName) {
        this.userShippingName = userShippingName;
    }

    public String getUserShippingStreet1() {
        return userShippingStreet1;
    }

    public void setUserShippingStreet1(String userShippingStreet1) {
        this.userShippingStreet1 = userShippingStreet1;
    }

    public String getUserShippingStreet2() {
        return userShippingStreet2;
    }

    public void setUserShippingStreet2(String userShippingStreet2) {
        this.userShippingStreet2 = userShippingStreet2;
    }

    public String getUserShippingCity() {
        return userShippingCity;
    }

    public void setUserShippingCity(String userShippingCity) {
        this.userShippingCity = userShippingCity;
    }

    public String getUserShippingState() {
        return userShippingState;
    }

    public void setUserShippingState(String userShippingState) {
        this.userShippingState = userShippingState;
    }

    public String getUserShippingCountry() {
        return userShippingCountry;
    }

    public void setUserShippingCountry(String userShippingCountry) {
        this.userShippingCountry = userShippingCountry;
    }

    public String getUserShippingZipCode() {
        return userShippingZipCode;
    }

    public void setUserShippingZipCode(String userShippingZipCode) {
        this.userShippingZipCode = userShippingZipCode;
    }

    public boolean isUserShippingDefault() {
        return userShippingDefault;
    }

    public void setUserShippingDefault(boolean userShippingDefault) {
        this.userShippingDefault = userShippingDefault;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
