package com.bookstore.dto.user;

/**
 * Created by Pc on 11/18/2017.
 */
public class UserForUpdate {

    private String firstName;

    private String lastName;

    private String email;

    public UserForUpdate() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
