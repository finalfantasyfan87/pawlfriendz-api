package com.pawfriendz.api.controller;


import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.bson.types.Binary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.pawfriendz.api.model.Photo;
import com.pawfriendz.api.service.PhotoService;
import com.pawfriendz.api.test.util.DTOStubUtil;
import com.pawfriendz.dto.PhotoDTO;
import com.pawfriendz.repository.PhotoRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(PhotoController.class)
public class PhotoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PhotoRepository mockPhotoRepo;
    @MockBean
    private PhotoService mockPhotoService;
    private PhotoDTO testPhotoDTO;
    
    @BeforeEach
    public void setup() {
    	testPhotoDTO = DTOStubUtil.getPhotoDTO();
    }
    
    @Test
    public void photo_Controller_Returns_201_Is_Created_When_Saved_Successfully() throws Exception {
    	when(mockPhotoService.savePhoto(testPhotoDTO)).thenReturn(Optional.of(new Photo("1234356789", new Binary(DTOStubUtil.getTestPhoto().getBytes()))));
    	mockMvc.perform(post("/photos/upload/").flashAttr("photoDTO", testPhotoDTO)).andExpect(status().isCreated());
    	verify(mockPhotoService, times(1)).savePhoto(testPhotoDTO);
    }
    
    @Test
    public void photo_Controller_Returns_422_Is_Unprocessable_Entity_When_Persistent_Exception() throws Exception {
    	when(mockPhotoService.savePhoto(testPhotoDTO)).thenReturn(Optional.ofNullable(null));
    	mockMvc.perform(post("/photos/upload/").flashAttr("photoDTO", testPhotoDTO)).andExpect(status().isUnprocessableEntity());
    	verify(mockPhotoService, times(1)).savePhoto(testPhotoDTO);
    }
    
    @Test
    public void photo_Controller_Throws_Exception_When_UserID_Is_Null() throws Exception {
    	testPhotoDTO.setUserId(null);
    	try {
    		mockMvc.perform(post("/photos/upload/").flashAttr("photoDTO", testPhotoDTO));
    		fail();
    	} catch (Exception e) {
    		verify(mockPhotoService, times(0)).savePhoto(testPhotoDTO);
    	}
    }
    
    @Test
    public void photo_Controller_Throws_Exception_When_UserID_Is_Empty() throws Exception {
    	testPhotoDTO.setUserId("");
    	try {
    		mockMvc.perform(post("/photos/upload/").flashAttr("photoDTO", testPhotoDTO));
    		fail();
    	} catch (Exception e) {
    		verify(mockPhotoService, times(0)).savePhoto(testPhotoDTO);
    	}
    } 
    
    @Test
    public void photo_Controller_Throws_Exception_When_Image_Is_Null() throws Exception {
    	testPhotoDTO.setPhoto(null);
    	try {
    		mockMvc.perform(post("/photos/upload/").flashAttr("photoDTO", testPhotoDTO));
    		fail();
    	} catch (Exception e) {
    		verify(mockPhotoService, times(0)).savePhoto(testPhotoDTO);
    	}
    }    
}