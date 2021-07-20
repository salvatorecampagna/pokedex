package com.truelayer.pokedex.mapper;

import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.details.model.Pokemon;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperImpl implements ObjectMapper {

    @Autowired
    private PokemonDescriptionProvider pokemonDescriptionProvider;

    @Override
    public Pokemon toPokemon(final PokemonDetails pokemonDetails) {
        return new Pokemon(
                pokemonDetails.getName(),
                pokemonDescriptionProvider.get(pokemonDetails),
                pokemonDetails.getHabitat() != null ? pokemonDetails.getHabitat().getName() : null,
                pokemonDetails.isLegendary()
        );
    }

    @Override
    public PokemonDto toPokemonDto(final Pokemon pokemon) {
        return new PokemonDto(
                pokemon.getName(),
                pokemon.getDescription(),
                pokemon.getHabitat(),
                pokemon.getLegendary()
        );
    }

    @Override
    public TranslatedPokemonDto toTranslatedPokemonDto(final TranslatedPokemon translatedPokemon) {
        return new TranslatedPokemonDto(
                translatedPokemon.getName(),
                translatedPokemon.getDescription(),
                translatedPokemon.getHabitat(),
                translatedPokemon.getTranslation(),
                translatedPokemon.getTranslated(),
                translatedPokemon.getLegendary()
        );
    }
}
