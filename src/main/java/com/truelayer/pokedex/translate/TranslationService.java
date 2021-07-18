package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.translate.model.TranslatedPokemon;

public interface TranslationService {
    TranslatedPokemon translate(TranslatedPokemon translatedPokemon);
}
