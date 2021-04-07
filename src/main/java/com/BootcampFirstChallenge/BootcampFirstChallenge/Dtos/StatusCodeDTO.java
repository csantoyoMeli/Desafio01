package com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusCodeDTO {
    private int code;
    private String title;
    private String message;
}
