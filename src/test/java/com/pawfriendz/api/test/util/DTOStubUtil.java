package com.pawfriendz.api.test.util;

import com.pawfriendz.dto.UserDTO;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DTOStubUtil {
	public static UserDTO getUserDTO() {
		UserDTO test = new UserDTO();
		test.setUserId("123456789");
		test.setFirstName("Barbie");
		test.setLastName("Barbie");
		test.setEmail("barbie@gmail.com");
		test.setPassword("ALongWayToBaSingSe99!");
		test.setUsername("barbz17");
		test.setPhoneNumber("555-555-5555");
		test.setFavoriteDog("doodle");
		test.setProfilePic(getTestPhoto());
		return test;
	}
	public static MockMultipartFile getTestPhoto()  {
		File testFile = new File("src/test/resources/barbie.jpeg");
		MockMultipartFile mockFile = null;
		try {
			mockFile = new MockMultipartFile("barbie.jpeg", Files.readAllBytes(testFile.toPath()));
		} catch (IOException e) {
	System.out.println(e.getMessage());
		}
		return mockFile;
	}

}
