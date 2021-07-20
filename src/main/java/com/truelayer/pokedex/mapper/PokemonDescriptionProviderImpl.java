package com.truelayer.pokedex.mapper;

import com.truelayer.pokedex.details.model.FlavorTextEntry;
import com.truelayer.pokedex.details.model.PokemonDetails;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class PokemonDescriptionProviderImpl implements PokemonDescriptionProvider {

    public static final String ENGLISH_LANGUAGE = "en";

    @Override
    public String get(final PokemonDetails pokemonDetails) {
        if (pokemonDetails.getFlavorTextEntries() == null || pokemonDetails.getFlavorTextEntries().isEmpty()) {
            return null;
        }
        final FlavorTextEntry flavorTextEntry = pokemonDetails.getFlavorTextEntries().stream().filter(
                entry -> ENGLISH_LANGUAGE.equalsIgnoreCase(entry.getLanguage().getName())
        ).findFirst().orElse(null);
        if (flavorTextEntry == null) {
            return null;
        }
        return flavorTextEntry.getFlavorText();
    }
}
