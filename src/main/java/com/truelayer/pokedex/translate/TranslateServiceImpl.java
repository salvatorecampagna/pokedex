package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.translate.model.PokemonSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslateServiceImpl implements TranslateService {
    @Autowired
    private TranslationClient translationClient;

    @Override
    public PokemonSummary translate(final PokemonSummary pokemonSummary) {
        final String translatedDescription = pokemonSummary.getLegendary() || "cave".equalsIgnoreCase(pokemonSummary.getHabitat()) ?
            translationClient.translate(pokemonSummary.getDescription(), "shakespeare") :
                translationClient.translate(pokemonSummary.getDescription(), "yoda");

        return new PokemonSummary(
                pokemonSummary.getName(),
                pokemonSummary.getHabitat(),
                translatedDescription,
                pokemonSummary.getLegendary()
        );
    }
}
