package com.truelayer.pokedex.details;

import com.truelayer.pokedex.details.model.PokemonDetails;

public interface PokemonDetailsClient {
    PokemonDetails getByIdOrName(String idOrName);
}
