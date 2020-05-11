package com.pawfriendz.api.test.util;

import com.pawfriendz.api.model.User;
import com.pawfriendz.dto.UserDTO;

public class DTOStubUtil {
	public static UserDTO getUserDTO() {
		return new UserDTO("123456789", "Uncle", "Iroh", "owner@greendragontea.com", "ALongWayToBaSingSe99!", "iroh1", "555-555-5555", "Zuko");
	}
}
