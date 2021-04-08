package com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Client;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.StatusCodeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewClientResponseDTO {
    private ClientDTO client;
    private StatusCodeDTO status;
}
