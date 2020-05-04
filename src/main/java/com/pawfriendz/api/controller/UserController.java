package com.pawfriendz.api.controller;

import com.pawfriendz.api.model.User;
import com.pawfriendz.api.service.UserService;
import com.pawfriendz.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

import static com.pawfriendz.api.util.PasswordUtil.hashPassword;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/register")
	public String sayHi(@Valid @RequestBody UserDTO userDTO) {
		if (userDTO != null) {
			User user = null;
			try {
				user = new User(userDTO.getUserId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
						hashPassword(userDTO.getPassword()), userDTO.getUsername());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			userService.saveUser(user);
			logger.debug("user " + user.getUserId() + " was saved to the database.");
		}

		return "Thank you for registering, " + (userDTO != null ? userDTO.getUsername() : null) + ".";
	}

}
