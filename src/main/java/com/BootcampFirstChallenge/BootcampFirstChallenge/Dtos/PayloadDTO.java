package com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayloadDTO {
    private List<PurchaseProductDTO> articles;
}
