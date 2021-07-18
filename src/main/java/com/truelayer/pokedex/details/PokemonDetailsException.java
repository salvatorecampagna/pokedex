package com.truelayer.pokedex.details;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class PokemonDetailsException extends RuntimeException {

    public PokemonDetailsException(final String message) {
        super(message);
    }

    public PokemonDetailsException(final String message, Throwable cause) {
        super(message, cause);
    }
}
