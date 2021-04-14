package com.BootcampFirstChallenge.BootcampFirstChallenge.Fixtures;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.ProductDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class ProductFixture {

    public static List<ProductDTO> allProductsList() {

        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:dbProductsTest.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ProductDTO>> typeReference = new TypeReference<List<ProductDTO>>() {
        };
        List<ProductDTO> products = null;

        try {
            products = objectMapper.readValue(file, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<ProductDTO> categoryFilterProductsList() {

        List<ProductDTO> pruducts = Arrays.asList(new ProductDTO(0, "Desmalezadora", "Herramientas", "Makita", 9600, 5, true, 4),
                new ProductDTO(2, "Soldadora", "Herramientas", "Black & Decker", 7215, 10, true, 3));

        return pruducts;
    }

    public static List<ProductDTO> eightProductsList() {
        List<ProductDTO> products = Arrays.asList(
                new ProductDTO(1, "Desmalezadora", "Herramientas", "Makita", 9600, 5, true, 4),
                new ProductDTO(2, "Taladro", "Herramientas", "Black & Decker", 12500, 7, false, 4),
                new ProductDTO(3, "Soldadora", "Herramientas", "Black & Decker", 7215, 10, true, 3),
                new ProductDTO(4, "Zapatillas", "Deportes", "Makita", 8900, 2, true, 3),
                new ProductDTO(5, "Camisa Runner", "Deportes", "Makita", 5000, 4, false, 4),
                new ProductDTO(6, "Liga", "Deportes", "Makita", 3000, 9, false, 4),
                new ProductDTO(7, "Moto E", "Celulares", "Motorola", 120000, 8, true, 5),
                new ProductDTO(8, "Lianix", "Celulares", "Huawei", 80000, 5, false, 2)
        );
        return products;
    }

    public static List<ProductDTO> filteredEightProductsList() {
        List<ProductDTO> products = Arrays.asList(
                new ProductDTO(1, "Desmalezadora", "Herramientas", "Makita", 9600, 5, true, 4),
                new ProductDTO(3, "Soldadora", "Herramientas", "Black & Decker", 7215, 10, true, 3)
        );
        return products;
    }

}
