package com.trustlayer.pokedex.details;

import com.trustlayer.pokedex.details.model.PokemonDetails;

public interface PokemonDetailsClient {
    PokemonDetails getByIdOrName(String idOrName);
}
