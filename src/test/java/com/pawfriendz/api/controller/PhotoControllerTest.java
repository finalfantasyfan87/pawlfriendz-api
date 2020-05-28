package com.pawfriendz.api.controller;


import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.pawfriendz.api.model.Photo;
import com.pawfriendz.api.service.PhotoService;
import com.pawfriendz.api.test.util.DTOStubUtil;
import com.pawfriendz.api.util.VaildationUtils;
import com.pawfriendz.dto.PhotoDTO;
import com.pawfriendz.repository.PhotoRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PhotoController.class)
public class PhotoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PhotoRepository mockPhotoRepo;
    @MockBean
    private PhotoService mockPhotoService;
    private PhotoDTO testPhotoDTO;
   
    
    @TestConfiguration
    static class TestConfig {
    	@Bean
    	public VaildationUtils getVaildationUtils() {
    		return new VaildationUtils();
    	}
    }
    
    
    @BeforeEach
    public void setup() {
    	testPhotoDTO = DTOStubUtil.getPhotoDTO();
    }
    
    @Test
    public void upload_Photo_Returns_201_Is_Created_When_Saved_Successfully() throws Exception {
    	when(mockPhotoService.savePhoto(testPhotoDTO)).thenReturn(Optional.of(new Photo("1234356789", new Binary(DTOStubUtil.getTestPhoto().getBytes()))));
    	mockMvc.perform(post("/photos/upload/").flashAttr("photoDTO", testPhotoDTO)).andExpect(status().isCreated());
    	verify(mockPhotoService, times(1)).savePhoto(testPhotoDTO);
    }
    
    @Test
    public void upload_Photo_Returns_422_Is_Unprocessable_Entity_When_Persistent_Exception() throws Exception {
    	when(mockPhotoService.savePhoto(testPhotoDTO)).thenReturn(Optional.ofNullable(null));
    	mockMvc.perform(post("/photos/upload/").flashAttr("photoDTO", testPhotoDTO)).andExpect(status().isUnprocessableEntity());
    	verify(mockPhotoService, times(1)).savePhoto(testPhotoDTO);
    }
    
    @Test
    public void upload_Photor_Throws_Exception_When_UserID_Is_Null() {
    	testPhotoDTO.setUserId(null);
    	try {
    		mockMvc.perform(post("/photos/upload/").flashAttr("photoDTO", testPhotoDTO));
    		fail();
    	} catch (Exception e) {
    		verify(mockPhotoService, times(0)).savePhoto(testPhotoDTO);
    	}
    }
    
    @Test
    public void upload_Photo_Throws_Exception_When_UserID_Is_Empty() {
    	testPhotoDTO.setUserId("");
    	try {
    		mockMvc.perform(post("/photos/upload/").flashAttr("photoDTO", testPhotoDTO));
    		fail();
    	} catch (Exception e) {
    		verify(mockPhotoService, times(0)).savePhoto(testPhotoDTO);
    	}
    } 
    
    @Test
    public void upload_Photo_Throws_Exception_When_Image_Is_Null() {
    	testPhotoDTO.setPhoto(null);
    	try {
    		mockMvc.perform(post("/photos/upload/").flashAttr("photoDTO", testPhotoDTO));
    		fail();
    	} catch (Exception e) {
    		verify(mockPhotoService, times(0)).savePhoto(testPhotoDTO);
    	}
    }
    
    @Test
    public void get_getAllPhotosForUser_Returns_Bad_Request_400_When_UserId_Is_Empty() {
    	try {
			mockMvc.perform(get("/photos/ ")).andExpect(status().isBadRequest());
		} catch (Exception e) {
			fail();
		}
    }
    
    @Test
    public void get_getAllPhotosForUser_Returns_Bad_Request_400_When_UserId_Has_Non_Alphanumeric_Characters() {
    	try {
			mockMvc.perform(get("/photos/123459-514b")).andExpect(status().isBadRequest());
		} catch (Exception e) {
			fail();
		}
    }
      
        
    @Test
    public void get_getAllPhotosForUsers_Returns_Good_Request_200_When_UserId_Contains_Valid_UserId() {
    	try {
			mockMvc.perform(get("/photos/123E4568AB")).andExpect(status().isOk());
		} catch (Exception e) {
			fail();
		}
    }
    
    
    @Test
    public void get_getSinglePhotoForUserById_Returns_Bad_Request_400_When_UserId_Is_Blank() {
    	try {
			mockMvc.perform(get("/photos/ /123E4568AB")).andExpect(status().isBadRequest());
		} catch (Exception e) {
			fail();
		}
    }
    
    @Test
    public void get_getSinglePhotoForUserById_Returns_Bad_Request_400_When_UserId_Contains_Non_Alphanumeric_Characters() {
    	try {
			mockMvc.perform(get("/photos/123459-514b/123E4568AB")).andExpect(status().isBadRequest());
		} catch (Exception e) {
			fail();
		}
    }
    
    @Test
    public void get_getSinglePhotoForUserById_Returns_Bad_Request_400_When_PhotoId_Is_Blank() {
    	try {
			mockMvc.perform(get("/photos/123E4568AB/ ")).andExpect(status().isBadRequest());
		} catch (Exception e) {
			fail();
		}
    }
    
    @Test
    public void get_getSinglePhotoForUserById_Returns_Bad_Request_400_When_PhotoId_Contains_Non_Alphanumeric_Characters() {
    	try {
			mockMvc.perform(get("/photos/123E4568AB/123459-514b")).andExpect(status().isBadRequest());
		} catch (Exception e) {
			fail();
		}
    }
    
    @Test
    public void get_getAllPhotosForUsers_Returns_Good_Request_204_When_UserId_And_PhotoId_Are_Valid_But_No_Photo_Was_Returned() {
    	try {
			mockMvc.perform(get("/photos/123E4568AB/123E4568AB")).andExpect(status().isNoContent());
		} catch (Exception e) {
			fail();
		}
    }
}