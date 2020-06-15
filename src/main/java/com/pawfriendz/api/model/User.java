package com.pawfriendz.api.model;

import com.pawfriendz.dto.UserDTO;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private String favoriteDog;
    private Binary profilePic;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String username, String phoneNumber, String favoriteDog, MultipartFile profilePic) throws IOException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.favoriteDog = favoriteDog;
        File testFile = new File("src/test/resources/dog.jpg");
        MockMultipartFile mockFile = new MockMultipartFile("dog.jpg", Files.readAllBytes(testFile.toPath()));
        this.profilePic = new Binary(mockFile.getBytes());
    //   this.profilePic =new Binary(profilePic.getBytes());
    }

    public User(UserDTO userDTO) throws IOException {
        this.userId = userDTO.getUserId();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.username = userDTO.getUsername();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.favoriteDog = userDTO.getFavoriteDog();
        this.profilePic= new Binary(userDTO.getProfilePic().getBytes());
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Binary getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Binary profilePic) {
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
        final StringBuilder sb = new StringBuilder("User{");
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

