package com.truelayer.pokedex.details;

public class PokemonDetailsException extends RuntimeException {

    public PokemonDetailsException(final String message) {
        super(message);
    }

    public PokemonDetailsException(final String message, Throwable cause) {
        super(message, cause);
    }
}
