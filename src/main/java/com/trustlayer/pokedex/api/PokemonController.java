package com.trustlayer.pokedex.api;

import com.trustlayer.pokedex.api.model.PokemonSummaryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "pokemon", produces = "application/json")
public class PokemonController {

    @GetMapping("/{pokemonName}")
    public PokemonSummaryDto getPokemon(@PathVariable("pokemonName") final String pokemonName) {
        return null;
    }

    @GetMapping("/translated/{pokemonName}")
    public PokemonSummaryDto getTranslatedPokemon(@PathVariable("pokemonName") final String pokemonName) {
        return null;
    }
}
