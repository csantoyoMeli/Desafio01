package com.BootcampFirstChallenge.BootcampFirstChallenge.Services.Product;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.*;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Product.*;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Ticket.TicketDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Entities.Criterion;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Product.ProductException;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.Product.ProductRepository;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Repository.Ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductServiceImpl implements ProductService {

    private final String[] avalibleParams = {"order", "product", "category", "brand", "price", "freeShipping", "prestige"};
    private AtomicLong uniqueLongId = new AtomicLong(System.currentTimeMillis());
    private Random random = new Random();

    @Autowired
    private ProductRepository articleRepository;
    @Autowired
    private TicketRepository ticketRepository;

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

        TicketDTO ticket = new TicketDTO(Math.abs(random.nextLong()) + uniqueLongId.incrementAndGet(),   // ID
                payload.getArticles(),  // Articles List
                getTotalPriceOfPayload(payload));   // Total price

        ticketRepository.addNewTicket(ticket);

        return new PayloadResponseDTO(ticket, new StatusCodeDTO(200,
                "SUCCESSFUL_OPERATION",
                "La solicitud de compra se completó con éxito"));
    }

    @Override
    public MarketCarDTO getMarketCar() {
        List<TicketDTO> ticketList = ticketRepository.getTickets();
        return new MarketCarDTO(ticketList, getTotalPriceOfTickets(ticketList));
    }

    private double getTotalPriceOfPayload(PayloadDTO payloadDTO) throws ProductException {
        double total = 0;
        for (PurchaseProductDTO article : payloadDTO.getArticles()) {
            total += article.getQuantity() * articleRepository.getProductToPurchase(article).getPrice();
        }
        return total;
    }

    private double getTotalPriceOfTickets(List<TicketDTO> tickets) {
        double total = 0;
        for (TicketDTO t : tickets) {
            total += t.getTotal();
        }
        return total;
    }
}
