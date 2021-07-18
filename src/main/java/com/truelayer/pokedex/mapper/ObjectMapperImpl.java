package com.truelayer.pokedex.mapper;

import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperImpl implements ObjectMapper {

    @Autowired
    private PokemonDescriptionProvider pokemonDescriptionProvider;

    @Override
    public PokemonDto toPokemonDto(final PokemonDetails pokemonDetails) {
        return new PokemonDto(
                pokemonDetails.getName(),
                pokemonDescriptionProvider.get(pokemonDetails),
                pokemonDetails.getHabitat().getName(),
                pokemonDetails.isLegendary()
        );
    }

    @Override
    public TranslatedPokemon toTranslatedPokemon(final PokemonDetails pokemonDetails) {
        return new TranslatedPokemon(
                pokemonDetails.getName(),
                pokemonDescriptionProvider.get(pokemonDetails),
                pokemonDetails.getHabitat().getName(),
                pokemonDetails.isLegendary()
        );
    }

    @Override
    public TranslatedPokemonDto toTranslatedPokemonDto(final TranslatedPokemon translatedPokemon) {
        return new TranslatedPokemonDto(
                translatedPokemon.getName(),
                translatedPokemon.getDescription(),
                translatedPokemon.getHabitat(),
                translatedPokemon.getLegendary()
        );
    }
}
