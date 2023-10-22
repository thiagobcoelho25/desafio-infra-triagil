package com.example.estagio_pokemon_triagil.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ResponseValidationError extends ResponseStandardError{
    private Map<String, String> campos;

    public ResponseValidationError(Long timestamp, HttpStatus status, String path, String message, Map<String, String> campos) {
        super(timestamp, status, path, message);
        this.campos = campos;
    }
}
