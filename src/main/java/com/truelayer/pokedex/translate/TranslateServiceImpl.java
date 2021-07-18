package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslateServiceImpl implements TranslateService {
    @Autowired
    private TranslationClient translationClient;

    @Override
    public TranslatedPokemon translate(final TranslatedPokemon translatedPokemon) {
        final String translatedDescription = translatedPokemon.getLegendary() || "cave".equalsIgnoreCase(translatedPokemon.getHabitat()) ?
            translationClient.translate(translatedPokemon.getDescription(), "shakespeare") :
                translationClient.translate(translatedPokemon.getDescription(), "yoda");

        return new TranslatedPokemon(
                translatedPokemon.getName(),
                translatedDescription,
                translatedPokemon.getHabitat(),
                translatedPokemon.getLegendary()
        );
    }
}
