package com.trustlayer.pokedex.mapper;

import com.trustlayer.pokedex.api.model.PokemonSummaryDto;
import com.trustlayer.pokedex.details.model.PokemonDetails;

public interface ObjectMapper {
    PokemonSummaryDto pokemonDetailsToPokemonSummaryDto(PokemonDetails pokemonDetails);
}
