package com.example.estagio_pokemon_triagil.exceptions;

import java.io.Serial;

public class BusinessRuleException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
//    private HttpStatus status_message;

//    public BusinessRuleException(String message, HttpStatus status_message) {
//        super(message);
//        this.status_message = status_message;
//    }
public BusinessRuleException(String message) {
        super(message);
    }

}
