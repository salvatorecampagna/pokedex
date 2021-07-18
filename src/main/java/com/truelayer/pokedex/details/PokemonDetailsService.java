package com.truelayer.pokedex.details;

import com.truelayer.pokedex.details.model.PokemonDetails;

public interface PokemonDetailsService {
    PokemonDetails getByIdOrName(String idOrName);
}
