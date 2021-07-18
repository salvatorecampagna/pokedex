package com.trustlayer.pokedex.translate;

import com.trustlayer.pokedex.translate.model.PokemonSummary;

public interface TranslateService {
    PokemonSummary translate(PokemonSummary pokemonSummary);
}
