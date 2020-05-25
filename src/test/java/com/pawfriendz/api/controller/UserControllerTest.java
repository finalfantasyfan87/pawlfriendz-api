package com.pawfriendz.api.controller;

import com.google.gson.Gson;
import com.pawfriendz.api.model.User;
import com.pawfriendz.api.service.UserService;
import com.pawfriendz.api.test.util.DTOStubUtil;
import com.pawfriendz.dto.UserDTO;
import com.pawfriendz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository mockUserRepo;
    @MockBean
    private UserService mockUserService;
    private UserDTO testUserDTO;
    @BeforeEach
    public void setup() {
        testUserDTO = DTOStubUtil.getUserDTO();
    }

    @Test
    public void user_Controller_Returns_201_Is_Created_When_Saved_Successfully() throws Exception {
        Gson gson = new Gson();
        String testUserDTOBody = gson.toJson(testUserDTO);
        mockMvc.perform(post("/register"). contentType(MediaType.APPLICATION_JSON).content(testUserDTOBody)    .characterEncoding("utf-8"))
        .andExpect(status().isOk());
        verify(mockUserService, times(1)).saveUser(new User(testUserDTO));
    }

}