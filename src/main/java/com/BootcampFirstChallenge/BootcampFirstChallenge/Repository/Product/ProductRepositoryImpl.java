package com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.Product;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.ProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.PurchaseProductDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.ProductException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List getProducts(List<Criterion> criteriaValues, String order) throws ProductException {
        List<ProductDTO> productsList = loadDataBase();         // LoadAllProductsFromFile

        // Apply Filter
        if (criteriaValues != null) {
            for (Criterion criterion : criteriaValues) {
                filterList(productsList, criterion);
            }
        }

        // Order values
        if (order != null)
            orderList(productsList, order);

        return productsList;
    }

    @Override
    public ProductDTO getProductToPurchase(PurchaseProductDTO article) throws ProductException {
        List<ProductDTO> productsList = loadDataBase();
        ProductDTO availableProduct = productsList.stream()
                .filter(product -> product.getProductId() == article.getProductId()
                        && product.getName().equals(article.getName())
                        && product.getBrand().equals(article.getBrand()))
                .findFirst().orElse(null);
        // Product Couldn be found
        if (availableProduct == null)
            throw new ProductException(ProductException.PRODUCT_COULD_NOT_BE_FOUND, ProductException.PRODUCT_COULD_NOT_BE_FOUND_MSG);
        // Product quantity avalibles is less than purchase request quantity
        if (availableProduct.getQuantity() < article.getQuantity())
            throw new ProductException(ProductException.QUANTITY_AVAILABLE_INSUFFICIENT, ProductException.QUANTITY_AVAILABLE_INSUFFICIENT_MSG);

        availableProduct.setQuantity(availableProduct.getQuantity() - article.getQuantity());
        uploadCSVFileByProduct(availableProduct);

        return availableProduct;
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
                        Integer.parseInt(fields[0]),    // ProductID
                        fields[1],  // Name
                        fields[2],  // CategoryName
                        fields[3],  // Brand
                        Double.parseDouble(fields[4].substring(1).replace(".", "")), // Price
                        Integer.parseInt(fields[5]),    // Quantity
                        ("SI".equals(fields[6])),   // FreeShipping
                        fields[7].length()          // Prestige
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
            case "product":    // Name Filter
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

    private void orderList(List<ProductDTO> list, String order) throws ProductException {
        int iOrder = Integer.parseInt(order);
        switch (iOrder) {
            case 0:
                Collections.sort(list, Comparator.comparing(ProductDTO::getName));
                break;
            case 1:
                Collections.sort(list, Comparator.comparing(ProductDTO::getName));
                Collections.reverse(list);
                break;
            case 2:
                Collections.sort(list, Comparator.comparing(ProductDTO::getPrice));
                Collections.reverse(list);
                break;
            case 3:
                Collections.sort(list, Comparator.comparing(ProductDTO::getPrice));
                break;
            default:
                throw new ProductException(ProductException.INVALID_INPUT, ProductException.INVALID_INPUT_MSG);
        }
    }

    private void uploadCSVFileByProduct(ProductDTO product) {

        BufferedReader bufferedReader = null;
        FileOutputStream fileOut = null;
        try {
            // Getting File
            File file = ResourceUtils.getFile("classpath:dbProductos.csv");
            bufferedReader = new BufferedReader(new FileReader(file));

            // Reading and adding first line
            String line = bufferedReader.readLine();
            StringBuilder inputBuffer = new StringBuilder();
            inputBuffer.append(line);
            inputBuffer.append("\n");

            while ((line = bufferedReader.readLine()) != null) {
                // Validating lines
                String[] csvValues = line.split(",");
                int id = Integer.parseInt(csvValues[0]);

                if (id == product.getProductId()) {
                    // Making an update of line
                    String updatedLine = product.getProductId() +
                            "," + product.getName() +
                            "," + product.getCategory() +
                            "," + product.getBrand() +
                            ",$" + (int)product.getPrice() +
                            "," + product.getQuantity() +
                            "," + (product.isFreeShipping() ? "SI" : "NO") +
                            "," + "*".repeat(product.getPrestige());

                    inputBuffer.append(updatedLine);
                    inputBuffer.append("\n");
                } else {
                    inputBuffer.append(line);
                    inputBuffer.append("\n");
                }
            }

            String inputStr = inputBuffer.toString();
            bufferedReader.close();

            // Write the new String with the replaced line OVER the same file
            fileOut = new FileOutputStream(file);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close bufferedReader
                if (bufferedReader != null)
                    bufferedReader.close();
                // Close fileOut
                if (fileOut != null)
                    fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
