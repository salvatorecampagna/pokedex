package com.truelayer.pokedex.details.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class PokemonDetailsServerException extends RuntimeException {

    public PokemonDetailsServerException(final Throwable e) {
        super(e);
    }
}
