package com.truelayer.pokedex.details.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PokemonDetailsClientException extends RuntimeException {

    public PokemonDetailsClientException(final Throwable e) {
        super(e);
    }
}
