package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private TranslationClientProvider translationClientProvider;

    @Override
    public TranslatedPokemon translate(final TranslatedPokemon translatedPokemon) {

        final TranslationClient client = translationClientProvider.get(
                translatedPokemon.getHabitat(),
                translatedPokemon.getLegendary()
        );
        return new TranslatedPokemon(
                translatedPokemon.getName(),
                client.translate(translatedPokemon.getDescription()),
                translatedPokemon.getHabitat(),
                translatedPokemon.getLegendary()
        );
    }
}
