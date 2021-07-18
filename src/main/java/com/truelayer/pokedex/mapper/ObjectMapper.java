package com.truelayer.pokedex.mapper;

import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.details.model.Pokemon;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;

public interface ObjectMapper {
    Pokemon toPokemon(PokemonDetails pokemonDetails);
    PokemonDto toPokemonDto(Pokemon pokemon);
    TranslatedPokemonDto toTranslatedPokemonDto(TranslatedPokemon translatedPokemon);
}
