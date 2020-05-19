package com.pawfriendz.api.controller;

import com.pawfriendz.api.model.User;
import com.pawfriendz.api.service.UserService;
import com.pawfriendz.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import static com.pawfriendz.api.util.PasswordUtil.hashPassword;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
		User user = null;
		if (userDTO != null) {
			user = new User(userDTO.getUserId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
					hashPassword(userDTO.getPassword()), userDTO.getUsername(), userDTO.getPhoneNumber(), userDTO.getFavoriteDog());
			userService.saveUser(user);
			logger.info(String.format("user id %s was saved to the database.", user.getUserId()));
		}
		return ResponseEntity.ok().body(user);

	}

	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody UserDTO userDto) {
		User loggedInUser = new User(userDto.getUsername(), userDto.getPassword());
		boolean userExist = userService.login(loggedInUser);
		logger.info(String.valueOf(userExist));
		return ResponseEntity.ok().body(loggedInUser);
	}
}