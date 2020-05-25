package com.pawfriendz.api.test.util;

import com.pawfriendz.dto.UserDTO;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DTOStubUtil {
	public static UserDTO getUserDTO() {
		return new UserDTO("123456789", "Barbie", "Jones", "barbie@gmail..com", "ALongWayToBaSingSe99!", "iroh1", "555-555-5555", "doodle", getTestPhoto());
	}
	public static MockMultipartFile getTestPhoto()  {
		File testFile = new File("src/test/resources/barbie.jpeg");
		MockMultipartFile mockFile = null;
		try {
			mockFile = new MockMultipartFile("barbie.jpeg", Files.readAllBytes(testFile.toPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mockFile;
	}

}
