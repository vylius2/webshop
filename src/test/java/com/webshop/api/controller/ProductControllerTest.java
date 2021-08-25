package com.webshop.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webshop.api.dto.request.CreateProductRequest;
import com.webshop.api.dto.request.UpdateProductRequest;
import com.webshop.persistance.entity.Product;
import com.webshop.persistance.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithUserDetails("vilius2")
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Product[] product = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product[].class);

        assertEquals(2, product.length);
        assertEquals("Agurkas", product[0].getName());
    }

    @Test
    public void getByIdTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Product product = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);

        assertEquals("Pomidoras", product.getName());
    }

    @Test
    public void createTest() throws Exception{
        CreateProductRequest createProductRequest = new CreateProductRequest("Agurkas", BigDecimal.valueOf(1), "Ilgas zalias", "C:/kompikas");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .content(objectMapper.writeValueAsString(createProductRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Product product = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);

        assertEquals(3L, product.getId());
    }

    @Test
    public void updateTest() throws Exception{
        UpdateProductRequest updateProductRequest = new UpdateProductRequest("Agurkas", BigDecimal.valueOf(1), "Ilgas zalias", "C:/kompikas");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", 1L)
                .content(objectMapper.writeValueAsString(updateProductRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Product product = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);

        assertEquals("Agurkas", product.getName());
    }

    @Test
    public void deleteTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
        assertFalse(productRepository.existsById(1L));
    }

}
