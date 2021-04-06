package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.ProductDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List getProducts(String categoryName, String freeShiping, String order) {
        List<ProductDTO> productsList = loadDataBase();         // LoadAllProductsFromFile

        // CategoryNameFilter
        if(categoryName != null) {
            for (Iterator<ProductDTO> it = productsList.iterator(); it.hasNext();) {
                if (!it.next().getCategory().contains(categoryName))
                    it.remove();
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
}
