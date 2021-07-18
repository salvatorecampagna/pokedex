package com.truelayer.pokedex.mapper;

import com.truelayer.pokedex.details.model.PokemonDetails;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class PokemonDescriptionProviderImpl implements PokemonDescriptionProvider {

    public static final String ENGLISH_LANGUAGE = "en";

    @Override
    public String get(final PokemonDetails pokemonDetails) {
        return pokemonDetails.getFlavorTextEntries().stream().filter(
                entry -> ENGLISH_LANGUAGE.equalsIgnoreCase(entry.getLanguage().getName())
        ).findFirst().orElseThrow(() -> new NoSuchElementException("No description found")).getFlavorText();
    }
}
