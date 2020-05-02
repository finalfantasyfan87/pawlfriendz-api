package com.pawfriendz.dto;

import javax.validation.constraints.*;

public class UserDTO {

    String userId;
    @NotBlank(message = "First Name is required.")
    String firstName;

    @NotBlank(message = "Last Name is required.")
    String lastName;

    @NotBlank(message = "Email is required.")
    @Email
    String email;

    @NotBlank(message="Password is required.")
    String password;

    @NotBlank(message="Username is required.")
    String username;

    public UserDTO(String userId, String firstName, String lastName, String email, String password, String userName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserDTO{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userName='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
