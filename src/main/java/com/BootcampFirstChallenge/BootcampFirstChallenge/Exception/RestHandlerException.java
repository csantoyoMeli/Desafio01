package com.BootcampFirstChallenge.BootcampFirstChallenge.Exception;

import com.BootcampFirstChallenge.BootcampFirstChallenge.Dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestHandlerException {
    @ExceptionHandler(ProductException.class)
    public ResponseEntity HandlerException(ProductException ex) {

        ErrorDTO errorDTO = new ErrorDTO(ex.getCode(), ex.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        switch (errorDTO.getErrorCode()){
            case ProductException.PARAMS_EXCESS:
                status = HttpStatus.METHOD_NOT_ALLOWED;
                break;
            case ProductException.INVALID_INPUT:
                status = HttpStatus.UNPROCESSABLE_ENTITY;
                break;
        }

        return new ResponseEntity(errorDTO, status);
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity NumberFormatException(NumberFormatException ex){
        ErrorDTO errorDTO = new ErrorDTO(ProductException.INVALID_INPUT, ProductException.INVALID_INPUT_MSG);
        return new ResponseEntity(errorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
