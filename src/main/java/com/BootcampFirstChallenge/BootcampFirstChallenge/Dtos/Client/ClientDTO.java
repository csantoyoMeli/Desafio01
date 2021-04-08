package com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.Client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDTO {
    private String clientId;
    private String name;
    private int age;
    private String province;
}
