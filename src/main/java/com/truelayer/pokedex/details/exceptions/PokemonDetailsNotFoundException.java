package com.truelayer.pokedex.details.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pokemon not found")
public class PokemonDetailsNotFoundException extends RuntimeException {

    public PokemonDetailsNotFoundException(final Throwable e) {
        super(e);
    }
}
