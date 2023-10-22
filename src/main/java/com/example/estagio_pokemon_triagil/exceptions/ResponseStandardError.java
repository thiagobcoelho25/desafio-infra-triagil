package com.example.estagio_pokemon_triagil.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStandardError {
        private Long timestamp;
        private HttpStatus status;
        private String path;
        private String message;
}
