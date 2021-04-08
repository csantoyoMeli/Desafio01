package com.BootcampFirstChallenge.BootcampFirstChallenge.Exception;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.StatusCodeDTO;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Client.ClientException;
import com.BootcampFirstChallenge.BootcampFirstChallenge.Exception.Product.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestHandlerException {
    @ExceptionHandler(ProductException.class)
    public ResponseEntity HandlerProductException(ProductException ex) {

        int intCode = 0;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        switch (ex.getCode()){
            case ProductException.PARAMS_EXCESS:
                status = HttpStatus.METHOD_NOT_ALLOWED;
                intCode = 405;
                break;
            case ProductException.INVALID_INPUT:
                status = HttpStatus.UNPROCESSABLE_ENTITY;
                intCode = 422;
                break;
            case ProductException.PRODUCT_COULD_NOT_BE_FOUND:
                status = HttpStatus.NOT_FOUND;
                intCode = 404;
                break;
            case ProductException.QUANTITY_AVAILABLE_INSUFFICIENT:
                status = HttpStatus.OK;
                intCode = 200;
                break;
        }
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO(intCode, ex.getCode(), ex.getMessage());
        return new ResponseEntity(statusCodeDTO, status);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity HandlerClientException(ClientException ex) {

        int intCode = 0;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        switch (ex.getCode()){
            case ClientException.VALUES_REQUIRED:
            case ClientException.INVALID_INPUT:
                status = HttpStatus.UNPROCESSABLE_ENTITY;
                intCode = 422;
                break;
            case ClientException.CLIENT_COULD_NOT_BE_FOUND:
                status = HttpStatus.NOT_FOUND;
                intCode = 404;
                break;
            case ClientException.CLIENT_ALREADY_EXISTS:
                status = HttpStatus.CONFLICT;
                intCode = 409;
                break;
        }
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO(intCode, ex.getCode(), ex.getMessage());
        return new ResponseEntity(statusCodeDTO, status);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity NumberFormatException(NumberFormatException ex){
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO(422, ProductException.INVALID_INPUT, ProductException.INVALID_INPUT_MSG);
        return new ResponseEntity(statusCodeDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
