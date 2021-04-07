package com.BootcampFirstChallenge.BootcampFirstChallenge.Exception;

import lombok.Data;

@Data
public class ProductException extends Exception {
    public static final String PARAMS_EXCESS = "PARAMS_EXCESS";
    public static final String PARAMS_EXCESS_MSG = "La aplicación solo admite un máximo de 2 parametros";
    public static final String INVALID_INPUT = "INVALID_INPUT";
    public static final String INVALID_INPUT_MSG = "No es posible procesar alguno de los parametros";
    public static final String PRODUCT_COULD_NOT_BE_FOUND = "PRODUCT_COULD_NOT_BE_FOUNDD";
    public static final String PRODUCT_COULD_NOT_BE_FOUND_MSG = "No se pudo encontrar el producto";
    public static final String QUANTITY_AVAILABLE_INSUFFICIENT = "INSUFICIENT_QUANTITY_AVALIBLE";
    public static final String QUANTITY_AVAILABLE_INSUFFICIENT_MSG = "No hay suficientes unidades disponibles del producto especificado";
    private String code;

    public ProductException(String code, String message) {
        super(message);
        this.code = code;
    }
}
