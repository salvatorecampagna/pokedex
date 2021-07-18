package com.trustlayer.pokedex.details;

import com.trustlayer.pokedex.details.model.PokemonDetails;

public interface PokemonDetailsService {
    PokemonDetails getByIdOrName(String idOrName);
}
