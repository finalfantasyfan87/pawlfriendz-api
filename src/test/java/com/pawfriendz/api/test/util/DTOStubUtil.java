package com.pawfriendz.api.test.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.mock.web.MockMultipartFile;

import com.pawfriendz.dto.PhotoDTO;
import com.pawfriendz.dto.UserDTO;

public class DTOStubUtil {
	public static UserDTO getUserDTO() {
		return new UserDTO("123456789", "Uncle", "Iroh", "owner@greendragontea.com", "ALongWayToBaSingSe99!", "iroh1", "555-555-5555", "Zuko");
	}
	
	public static PhotoDTO getPhotoDTO() {
		return new PhotoDTO("123456789", "234567890", getTestPhoto());
	}
		
	public static MockMultipartFile getTestPhoto()  {
		File testFile = new File("src/test/resources/dog.jpg");
		MockMultipartFile mockFile = null;
		try {
			mockFile = new MockMultipartFile("dog.jpg", Files.readAllBytes(testFile.toPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mockFile;
	}
}
