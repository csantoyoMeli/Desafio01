package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.ProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List getProducts(List<Criterion> criteriaValues, String order) {
        List<ProductDTO> productsList = loadDataBase();         // LoadAllProductsFromFile

        // Apply Filter
        if (criteriaValues != null) {
            for (Criterion criterion : criteriaValues) {
                filterList(productsList, criterion);
            }
        }

        return productsList;
    }

    private List<ProductDTO> loadDataBase() {
        List<ProductDTO> productsList = new ArrayList();

        BufferedReader bufferReader = null;
        try {
            // Open csv to read
            File file = ResourceUtils.getFile("classpath:dbProductos.csv");
            bufferReader = new BufferedReader(new FileReader(file));
            // Read a line from file
            bufferReader.readLine();
            // Need data from second line of our file
            String line = bufferReader.readLine();

            while (line != null) {
                // Split lines
                String[] fields = line.split(",");

                // Creating a new ProductDTO
                ProductDTO productDTO = new ProductDTO(
                        fields[0],  // Name
                        fields[1],  // CategoryName
                        fields[2],  // Brand
                        Double.parseDouble(fields[3].substring(1).replace(".", "")), // Price
                        Integer.parseInt(fields[4]),    // Quantity
                        ("SI".equals(fields[5])),   // FreeShipping
                        fields[6].length()          // Prestige
                );
                productsList.add(productDTO);
                line = bufferReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close bufferedReader
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return productsList;
    }

    private void filterList(List<ProductDTO> list, Criterion criterion) {
        switch (criterion.getType()) {
            case "name":    // Name Filter
                list.removeIf(productDTO -> !productDTO.getName().contains(criterion.getValue()));
                break;
            case "category":    // Category Filter
                list.removeIf(productDTO -> !productDTO.getCategory().contains(criterion.getValue()));
                break;
            case "freeShipping":    // Free Shipping Filter
                boolean freeShippingValue = Boolean.parseBoolean(criterion.getValue());
                list.removeIf(productDTO -> productDTO.isFreeShipping() != freeShippingValue);
                break;
            case "brand":    // Brand Filter
                list.removeIf(productDTO -> !productDTO.getBrand().contains(criterion.getValue()));
                break;
            case "price":    // Price Filter
                double priceValue = Double.parseDouble(criterion.getValue());
                list.removeIf(productDTO -> productDTO.getPrice() > priceValue);
                break;
            case "prestige":    // Prestige Filter
                int prestigeValue = Integer.parseInt(criterion.getValue());
                list.removeIf(productDTO -> productDTO.getPrestige() != prestigeValue);
                break;
        }

    }
}
