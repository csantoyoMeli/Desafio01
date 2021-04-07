package com.BootcampFirstChallenge.BootcampFirstChallenge.Services;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.*;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.ProductException;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductServiceImpl implements ProductService {

    private final String[] avalibleParams = {"order", "product", "category", "brand", "price", "freeShipping", "prestige"};

    @Autowired
    private ProductRepository articleRepository;

    @Override
    public List<ProductDTO> getProducts(Map<String, String> allParams) throws ProductException {
        // Getting just 2 criteria
        List<Criterion> criteria = new ArrayList<>();
        int criteriaCounter = 0;
        String order = null;

        if (allParams != null) {
            for (Map.Entry<String, String> entry : allParams.entrySet()) {
                // Validate params
                if (!Arrays.stream(avalibleParams).anyMatch(val -> val.equals(entry.getKey())))
                    throw new ProductException(ProductException.INVALID_INPUT, ProductException.INVALID_INPUT_MSG);

                if (entry.getKey().equals(avalibleParams[0])) {     // avalibleParams[0] is "order"
                    order = entry.getValue();
                } else {
                    // Add a criterion
                    criteria.add(new Criterion(entry.getValue(), entry.getKey()));
                    criteriaCounter++;
                }
            }
        }
        // Params Excess exception
        if (criteriaCounter > 2)
            throw new ProductException(ProductException.PARAMS_EXCESS, ProductException.PARAMS_EXCESS_MSG);

        return articleRepository.getProducts(criteria, order);
    }

    @Override
    public PayloadResponseDTO purchaseRequest(PayloadDTO payload) throws ProductException {
        AtomicLong uniqueLongId = new AtomicLong(System.currentTimeMillis());
        Random random = new Random();
        long uniqueID = Math.abs(random.nextLong()) + uniqueLongId.incrementAndGet();
        TicketDTO ticket = new TicketDTO(uniqueID, payload.getArticles(), getTotalPriceOfPayload(payload));
        return new PayloadResponseDTO(ticket, new StatusCodeDTO(200, "SUCCESS_OPERATION", "La solicitud de compra se completó con éxito"));
    }

    private double getTotalPriceOfPayload(PayloadDTO payloadDTO) throws ProductException {
        double total = 0;
        for(PurchaseProductDTO article: payloadDTO.getArticles()){
            total += article.getQuantity() * articleRepository.getProductToPurchase(article).getPrice();
        }
        return total;
    }
}
