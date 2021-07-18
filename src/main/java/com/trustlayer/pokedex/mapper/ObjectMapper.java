package com.trustlayer.pokedex.mapper;

import com.trustlayer.pokedex.api.model.PokemonSummaryDto;
import com.trustlayer.pokedex.translate.model.PokemonSummary;
import com.trustlayer.pokedex.details.model.PokemonDetails;

public interface ObjectMapper {
    PokemonSummaryDto pokemonDetailsToPokemonSummaryDto(PokemonSummary pokemonSummary);
    PokemonSummaryDto pokemonDetailsToPokemonSummaryDto(PokemonDetails pokemonDetails);
    PokemonSummary pokemonDetailsToPokemonSummary(PokemonDetails pokemonDetails);
}
