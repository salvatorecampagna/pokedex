package com.trustlayer.pokedex.api;

import com.trustlayer.pokedex.api.model.PokemonSummaryDto;
import com.trustlayer.pokedex.mapper.ObjectMapper;
import com.trustlayer.pokedex.details.PokemonDetailsService;
import com.trustlayer.pokedex.translate.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "pokemon", produces = "application/json")
public class PokemonController {

    @Autowired
    private PokemonDetailsService pokemonDetailsService;

    @Autowired
    private TranslateService translateService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{pokemonName}")
    public PokemonSummaryDto getPokemon(@PathVariable("pokemonName") final String pokemonName) {
        return objectMapper.pokemonDetailsToPokemonSummaryDto(
                pokemonDetailsService.getByIdOrName(pokemonName)
        );
    }

    @GetMapping("/translated/{pokemonName}")
    public PokemonSummaryDto getTranslatedPokemon(@PathVariable("pokemonName") final String pokemonName) {
        return objectMapper.pokemonDetailsToPokemonSummaryDto(
                translateService.translate(
                    objectMapper.pokemonDetailsToPokemonSummary(
                            pokemonDetailsService.getByIdOrName(pokemonName)
                    )
                )
        );
    }
}
