package com.truelayer.pokedex.mapper;

import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class ObjectMapperImpl implements ObjectMapper {

    @Override
    public PokemonDto toPokemonDto(final PokemonDetails pokemonDetails) {
        return new PokemonDto(
                pokemonDetails.getName(),
                getPokemonDescription(pokemonDetails),
                pokemonDetails.getHabitat().getName(),
                pokemonDetails.isLegendary()
        );
    }

    @Override
    public TranslatedPokemon toTranslatedPokemon(final PokemonDetails pokemonDetails) {
        return new TranslatedPokemon(
                pokemonDetails.getName(),
                getPokemonDescription(pokemonDetails),
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

    private String getPokemonDescription(final PokemonDetails pokemonDetails) {
        return pokemonDetails.getFlavorTextEntries().stream().filter(
                entry -> "en".equalsIgnoreCase(entry.getLanguage().getName())
        ).findFirst().orElseThrow(() -> new NoSuchElementException("No english description found")).getFlavorText();
    }
}
