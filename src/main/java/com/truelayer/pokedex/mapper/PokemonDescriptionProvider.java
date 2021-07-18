package com.truelayer.pokedex.mapper;

import com.truelayer.pokedex.details.model.PokemonDetails;

public interface PokemonDescriptionProvider {
    String get(PokemonDetails pokemonDetails);
}
