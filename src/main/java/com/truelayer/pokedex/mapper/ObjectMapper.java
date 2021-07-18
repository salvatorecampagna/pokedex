package com.truelayer.pokedex.mapper;

import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;

public interface ObjectMapper {
    PokemonDto toPokemonDto(PokemonDetails pokemonDetails);
    TranslatedPokemon toTranslatedPokemon(PokemonDetails pokemonDetails);
    TranslatedPokemonDto toTranslatedPokemonDto(TranslatedPokemon translatedPokemon);
}
