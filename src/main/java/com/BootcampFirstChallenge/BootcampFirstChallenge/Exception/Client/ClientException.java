package com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Client;

import lombok.Data;

import java.io.IOException;

@Data
public class ClientException extends IOException {

    public static final String INVALID_INPUT = "INVALID_INPUT";
    public static final String INVALID_INPUT_MSG = "No es posible procesar alguno de los parametros";
    public static final String CLIENT_COULD_NOT_BE_FOUND = "CLIENT_COULD_NOT_BE_FOUND";
    public static final String CLIENT_COULD_NOT_BE_FOUND_MSG = "No se pudo encontrar el cliente";
    public static final String VALUES_REQUIRED = "VALUES_REQUIRED";
    public static final String VALUES_REQUIRED_MSG = "Hacen faltan valores necesarios para la operación";
    public static final String CLIENT_ALREADY_EXISTS = "CLIENT_ALREADY_EXISTS";
    public static final String CLIENT_ALREADY_EXISTS_MSG = "El ID que se ha ingresado ya existe";

    private String code;

    public ClientException(String code, String message) {
        super(message);
        this.code = code;
    }
}
