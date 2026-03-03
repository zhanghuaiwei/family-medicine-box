package com.medicinebox.user.controller;

import com.medicinebox.common.model.User;
import com.medicinebox.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setId("123");
        user.setUsername("test");
        user.setPhone("13800138000");
        user.setPassword("password");

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"test\",\"phone\":\"13800138000\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("123"))
                .andExpect(jsonPath("$.data.username").value("test"));
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User();
        user.setId("123");
        user.setUsername("test");

        when(userService.getUserById("123")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("123"))
                .andExpect(jsonPath("$.data.username").value("test"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser("123");

        mockMvc.perform(delete("/api/users/123"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId("123");
        user1.setUsername("test1");
        users.add(user1);

        User user2 = new User();
        user2.setId("456");
        user2.setUsername("test2");
        users.add(user2);

        when(userService.getAllUsers(0)).thenReturn(users);

        mockMvc.perform(get("/api/users?deleted=0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].username").value("test1"))
                .andExpect(jsonPath("$.data[1].username").value("test2"));
    }

    @Test
    void testLogin() throws Exception {
        User user = new User();
        user.setId("123");
        user.setUsername("test");

        when(userService.login("13800138000", "password")).thenReturn(user);

        mockMvc.perform(post("/api/users/login")
                .param("phone", "13800138000")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("123"))
                .andExpect(jsonPath("$.data.username").value("test"));
    }
}
