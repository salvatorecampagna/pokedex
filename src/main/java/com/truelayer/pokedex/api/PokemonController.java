package com.truelayer.pokedex.api;

import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.mapper.ObjectMapper;
import com.truelayer.pokedex.translate.TranslateService;
import com.truelayer.pokedex.details.PokemonDetailsService;
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
    public PokemonDto getPokemon(@PathVariable("pokemonName") final String pokemonName) {
        return objectMapper.toPokemonDto(
                pokemonDetailsService.getByIdOrName(pokemonName)
        );
    }

    @GetMapping("/translated/{pokemonName}")
    public TranslatedPokemonDto getTranslatedPokemon(@PathVariable("pokemonName") final String pokemonName) {
        return objectMapper.toTranslatedPokemonDto(
                translateService.translate(
                    objectMapper.toTranslatedPokemon(
                            pokemonDetailsService.getByIdOrName(pokemonName)
                    )
                )
        );
    }
}
