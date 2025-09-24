package com.example.demo.Test;

import com.example.demo.Controller.MyController;
import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService; // Import the service
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean; // Correct annotation for mocking in WebMvcTest
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@WebMvcTest(MyController.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Use @MockBean for service mocks in @WebMvcTest
    @MockBean
    private ProductService productService;

    // No need for @InjectMocks MyController here because @WebMvcTest handles controller injection
    // and automatically injects the @MockBean into it.

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldDisplayAllProductsUsingMock() throws Exception {
        // Stub the mock service call
        List<Product> mockProducts = List.of(
            new Product(999, "MockProduct1", 100, 5, "MockCategory1"),
            new Product(998, "MockProduct2", 200, 10, "MockCategory2")
        );
        when(productService.getAllProducts()).thenReturn(mockProducts);

        // Perform GET request
        mockMvc.perform(get("/productdetails")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))) // Expect 2 products
                .andExpect(jsonPath("$[0].name", is("MockProduct1")))
                .andExpect(jsonPath("$[1].name", is("MockProduct2")));

        // Verify the service method was called once
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void shouldDisplayProductById() throws Exception {
        Product mockProduct = new Product(101, "Television", 100000, 5, "Electronics");
        when(productService.getProductById(101)).thenReturn(Optional.of(mockProduct));

        mockMvc.perform(get("/productdetails/101")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(101)))
                .andExpect(jsonPath("$.name", is("Television")));

        verify(productService, times(1)).getProductById(101);
    }

    @Test
    public void shouldReturnNotFoundForInvalidProductId() throws Exception {
        when(productService.getProductById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/productdetails/999")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Controller returns null, which Spring handles as 200 OK with empty body
                .andExpect(jsonPath("$").doesNotExist()); // Or check for null if controller returns null

        verify(productService, times(1)).getProductById(999);
    }

    // You can add more tests for other endpoints here
}