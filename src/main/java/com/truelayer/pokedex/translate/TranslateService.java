package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.translate.model.PokemonSummary;

public interface TranslateService {
    PokemonSummary translate(PokemonSummary pokemonSummary);
}
