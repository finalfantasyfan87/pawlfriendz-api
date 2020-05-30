package com.pawfriendz.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserDTO {

    private String userId;
    @NotBlank(message = "First Name is required.")
    private String firstName;

    @NotBlank(message = "Last Name is required.")
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email
    private String email;

    @NotBlank(message = "Password is required.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\p{Punct})[\\p{ASCII}&&[\\S]]{8,}$", message = "Your password must be between 8 and 10,at least one lowercase letter, at least one digit: between  0-9, at least one special character, and at least one capital letter.")
    private String password;

    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Phone number is required.")
            @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message= "Please enter phone number in xxx-xxx-xxxx format. Numbers only, please!")
    private String phoneNumber;
    private String  favoriteDog;


    private MultipartFile profilePic;

    public UserDTO() {
    }

    public UserDTO( @NotBlank(message = "First Name is required.") String firstName, @NotBlank(message = "Last Name is required.") String lastName, @NotBlank(message = "Email is required.") @Email String email, @NotBlank(message = "Password is required.") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\p{Punct})[\\p{ASCII}&&[\\S]]{8,}$", message = "Your password must be between 8 and 10,at least one lowercase letter, at least one digit: between  0-9, at least one special character, and at least one capital letter.") String password, @NotBlank(message = "Username is required.") String username, @NotBlank(message = "Phone number is required.") @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Please enter phone number in xxx-xxx-xxxx format. Numbers only, please!") String phoneNumber, String favoriteDog, MultipartFile profilePic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.favoriteDog = favoriteDog;
        this.profilePic = profilePic;
    }

    public UserDTO(@NotBlank(message = "Password is required.") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\p{Punct})[\\p{ASCII}&&[\\S]]{8,}$", message = "Your password must be between 8 and 10,at least one lowercase letter, at least one digit: between  0-9, at least one special character, and at least one capital letter.") String password, @NotBlank(message = "Username is required.") String username) {
        this.password = password;
        this.username = username;
    }

    public MultipartFile getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(MultipartFile profilePic) {
        this.profilePic = profilePic;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFavoriteDog() {
        return favoriteDog;
    }

    public void setFavoriteDog(String favoriteDog) {
        this.favoriteDog = favoriteDog;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserDTO{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", favoriteDog='").append(favoriteDog).append('\'');
        sb.append(", profilePic=").append(profilePic);
        sb.append('}');
        return sb.toString();
    }
}
