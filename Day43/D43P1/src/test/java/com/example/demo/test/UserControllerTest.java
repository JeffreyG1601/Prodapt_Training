package com.example.demo.test; // The package from your screenshot

// Make sure all these imports are present
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Controller.UserController;
import com.example.demo.model.User;
import com.example.demo.service.UserService;



// 1. Target the UserController class for this test
@WebMvcTest(UserController.class)
public class UserControllerTest {

    // 2. Inject MockMvc to perform fake API calls
    @Autowired
    private MockMvc mockMvc;

    // 3. Create a mock of the UserService dependency
    @MockBean
    private UserService userService;

    @Test
    public void testGetUserById_whenUserExists() throws Exception {
        // Arrange: Set up your test data and mock behavior
        User user = new User(1L, "Jane Doe", "jane.doe@example.com");

        // Stub the service method call
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        // Act & Assert: Perform the request and check the response
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
    }

    @Test
    public void testGetUserById_whenUserDoesNotExist() throws Exception {
        // Arrange: Stub the service to return nothing
        when(userService.getUserById(99L)).thenReturn(Optional.empty());

        // Act & Assert: Perform the request and check for a 404
        mockMvc.perform(get("/api/users/99"))
                .andExpect(status().isNotFound());
    }
}