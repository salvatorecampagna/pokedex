package com.truelayer.pokedex.api;

import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.mapper.ObjectMapper;
import com.truelayer.pokedex.translate.TranslationService;
import com.truelayer.pokedex.details.PokemonDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private TranslationService translationService;

    @Autowired
    private ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(PokemonController.class);

    @GetMapping("/{pokemonName}")
    public PokemonDto getPokemon(@PathVariable("pokemonName") final String pokemonName) {
        logger.info(String.format("GET /pokemon/%s", pokemonName));
        return objectMapper.toPokemonDto(
                pokemonDetailsService.getByIdOrName(pokemonName)
        );
    }

    @GetMapping("/translated/{pokemonName}")
    public TranslatedPokemonDto getTranslatedPokemon(@PathVariable("pokemonName") final String pokemonName) {
        logger.info(String.format("GET /translated/%s", pokemonName));
        return objectMapper.toTranslatedPokemonDto(
                translationService.translate(
                        pokemonDetailsService.getByIdOrName(pokemonName)
                )
        );
    }
}
