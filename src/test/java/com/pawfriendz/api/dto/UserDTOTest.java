package com.pawfriendz.api.dto;

import com.pawfriendz.api.test.util.DTOStubUtil;
import com.pawfriendz.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDTOTest {
	
	private Validator validator;

	@BeforeEach
	public void setup() {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	        validator = factory.getValidator();
	}
	
	@Test
	public void test_User_DTO_Validation_For_Good_DTO() {
		UserDTO user = DTOStubUtil.getUserDTO();
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
		System.out.println(violations);
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void test_User_DTO_Validation_For_DTO_With_Blank_First_Name() {
		UserDTO user = DTOStubUtil.getUserDTO();
		user.setFirstName("");
		validateUserHasViolations(user);
	}
	
	
	@Test
	public void test_User_DTO_Validation_For_DTO_With_Blank_Last_Name() {
		UserDTO user = DTOStubUtil.getUserDTO();
		user.setLastName("");
		validateUserHasViolations(user);
	}
	
	@Test
	public void test_User_DTO_Validation_For_DTO_With_Blank_Password() {
		UserDTO user = DTOStubUtil.getUserDTO();
		user.setPassword("");
		validateUserHasViolations(user);
	}
	
	@Test
	public void test_User_DTO_Validation_For_DTO_With_No_Lowercase_Letters_In_Password() {
		UserDTO user = DTOStubUtil.getUserDTO();
		user.setPassword("HELLOFROMTHEOTHERSIDE%123");
		validateUserHasViolations(user);
	}
	
	@Test
	public void test_User_DTO_Validation_For_DTO_With_No_Uppercase_Letters_In_Password() {
		UserDTO user = DTOStubUtil.getUserDTO();
		user.setPassword("hellofromtheotherside%123");
		validateUserHasViolations(user);
	}
	
	@Test
	public void test_User_DTO_Validation_For_DTO_With_No_Digits_In_Password() {
		UserDTO user = DTOStubUtil.getUserDTO();
		user.setPassword("HelloFromTheOtherSide%");
		validateUserHasViolations(user);
	}
	
	
	@Test
	public void test_User_DTO_Validation_For_DTO_With_No_Special_Characters_In_Password() {
		UserDTO user = DTOStubUtil.getUserDTO();
		user.setPassword("HelloFromTheOtherSide123");
		validateUserHasViolations(user);
	}
	
	@Test
	public void test_User_DTO_Validation_For_DTO_With_Letters_In_Phone_Number() {
		UserDTO user = DTOStubUtil.getUserDTO();
		user.setPhoneNumber("");
		validateUserHasViolations(user);
	}
	
	@Test
	public void test_User_DTO_Validation_For_DTO_With_No_Dashes_Number() {
		UserDTO user = DTOStubUtil.getUserDTO();
		user.setPhoneNumber("8475555213");
		validateUserHasViolations(user);
	}
	
	@Test
	public void test_User_DTO_Validation_For_DTO_With_One_Dash_In_Phone_Number() {
		UserDTO user = DTOStubUtil.getUserDTO();
		user.setPhoneNumber("847-5555213");
		validateUserHasViolations(user);
	}
	
	@Test
	public void test_User_DTO_Validation_For_DTO_With_Two_Misplaced_Dashes_In_Phone_Number() {
		UserDTO user = DTOStubUtil.getUserDTO();
		user.setPhoneNumber("84-75555-213");
		validateUserHasViolations(user);
	}
	
	private void validateUserHasViolations(UserDTO user) {
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
		assertTrue(!violations.isEmpty());
	}
}
