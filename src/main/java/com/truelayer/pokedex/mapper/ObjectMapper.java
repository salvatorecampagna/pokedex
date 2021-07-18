package com.truelayer.pokedex.mapper;

import com.truelayer.pokedex.api.model.PokemonSummaryDto;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.translate.model.PokemonSummary;

public interface ObjectMapper {
    PokemonSummaryDto pokemonDetailsToPokemonSummaryDto(PokemonSummary pokemonSummary);
    PokemonSummaryDto pokemonDetailsToPokemonSummaryDto(PokemonDetails pokemonDetails);
    PokemonSummary pokemonDetailsToPokemonSummary(PokemonDetails pokemonDetails);
}
