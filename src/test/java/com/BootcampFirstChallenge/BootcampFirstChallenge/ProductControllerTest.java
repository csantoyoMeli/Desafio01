package com.BootcampFirstChallenge.BootcampFirstChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Controllers.Product.ProductController;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.ProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Fixtures.ProductFixture;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Services.Product.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(MockitoJUnitRunner.class)
@EnableWebMvc

public class ProductControllerTest {
    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductServiceImpl service;

    private MockMvc mvc;
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void allProductsTest() throws Exception {
        List<ProductDTO> productsList = ProductFixture.allProductsList();

        when(service.getProducts(anyMap())).thenReturn(productsList);

        MvcResult result = mvc.perform(get("/api/v1/articles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertNotNull(response);

        List<ProductDTO> listResponse = Arrays.asList(mapper.readValue(response, ProductDTO[].class));
        assertNotNull(listResponse);

        verify(service, times(1)).getProducts(anyMap());
        assertEquals(productsList, listResponse);
    }

    @Test
    public void categoryFilterTest() throws Exception {
        List<ProductDTO> productsList = ProductFixture.categoryFilterProductsList();
        Map<String, String> params = new HashMap();
        params.put("category", "Herramientas");

        when(service.getProducts(params)).thenReturn(productsList);

        MvcResult result = mvc.perform(get("/api/v1/articles?category=Herramientas").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertNotNull(response);

        List<ProductDTO> listResponse = Arrays.asList(mapper.readValue(response, ProductDTO[].class));
        assertNotNull(listResponse);

        verify(service, times(1)).getProducts(params);
        assertEquals(productsList, listResponse);
    }

    @Test
    public void categoryFreeShippingFilterTest() throws Exception {

        List<ProductDTO> filteredList = ProductFixture.filteredEightProductsList();

        Map<String, String> params = new HashMap();
        params.put("category", "Herramientas");
        params.put("freeShipping", "true");

        when(service.getProducts(params)).thenReturn(filteredList);

        String url = "/api/v1/articles?category=Herramientas&freeShipping=true";
        MvcResult result = mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertNotNull(response);

        List<ProductDTO> listResponse = Arrays.asList(mapper.readValue(response, ProductDTO[].class));
        assertNotNull(listResponse);

        verify(service, times(1)).getProducts(params);
        assertEquals(filteredList, listResponse);
    }
}
