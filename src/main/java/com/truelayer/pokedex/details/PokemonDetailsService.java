package com.truelayer.pokedex.details;

import com.truelayer.pokedex.details.model.Pokemon;

public interface PokemonDetailsService {
    Pokemon getByIdOrName(String idOrName);
}
