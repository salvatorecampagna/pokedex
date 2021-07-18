package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.translate.model.TranslatedPokemon;

public interface TranslateService {
    TranslatedPokemon translate(TranslatedPokemon translatedPokemon);
}
