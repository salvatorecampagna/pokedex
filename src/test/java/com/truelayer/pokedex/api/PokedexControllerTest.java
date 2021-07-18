package com.truelayer.pokedex.api;

import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.details.PokemonDetailsException;
import com.truelayer.pokedex.details.PokemonDetailsService;
import com.truelayer.pokedex.details.model.Pokemon;
import com.truelayer.pokedex.mapper.ObjectMapper;
import com.truelayer.pokedex.translate.TranslationService;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.truelayer.pokedex.TestUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PokedexControllerTest {
    @MockBean
    TranslationService translationService;

    @MockBean
    PokemonDetailsService pokemonDetailsService;

    @MockBean
    ObjectMapper objectMapper;

    com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnPokemon() throws Exception {
        // GIVEN
        final String pokemonName = "pikachu";
        final Pokemon pokemon = buildPokemon(
                "Pikachu",
                "Dangerous habitat",
                "One of the most popular Pokemons",
                false
        );
        final PokemonDto pokemonDto = buildPokemonDto(pokemon);

        when(pokemonDetailsService.getByIdOrName(ArgumentMatchers.matches(pokemonName)))
                .thenReturn(pokemon);
        when(objectMapper.toPokemonDto(ArgumentMatchers.eq(pokemon)))
                .thenReturn(pokemonDto);

        //WHEN
        final ResultActions resultActions = mockMvc.perform(
                get(String.format("/pokemon/%s", pokemonName))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //THEN
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(pokemonDto)));
    }

    @Test
    public void shouldReturnTranslatedPokemon() throws Exception {
        // GIVEN
        final String pokemonName = "bulbasaur";
        final Pokemon pokemon = buildPokemon(
                "Balbasaur",
                "The forest",
                "Another Pokemon that I heard of",
                true
        );
        final TranslatedPokemon translatedPokemon = buildTranslatedPokemon(
                pokemon.getName(),
                pokemon.getDescription().toLowerCase(),
                pokemon.getHabitat(),
                "lowercase",
                true,
                pokemon.getLegendary()
        );
        final TranslatedPokemonDto translatedPokemonDto = buildTranslatedPokemonDto(translatedPokemon);

        when(pokemonDetailsService.getByIdOrName(ArgumentMatchers.matches(pokemonName)))
                .thenReturn(pokemon);
        when(translationService.translate(pokemon))
            .thenReturn(translatedPokemon);
        when(objectMapper.toTranslatedPokemonDto(ArgumentMatchers.eq(translatedPokemon)))
                .thenReturn(translatedPokemonDto);

        //WHEN
        final ResultActions resultActions = mockMvc.perform(
                get(String.format("/pokemon/translated/%s", pokemonName))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //THEN
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(translatedPokemonDto)));
    }

    @Test
    public void shouldReturn5xxOnPokemonDetailsRestApiCallError() throws Exception {
        // GIVEN
        when(pokemonDetailsService.getByIdOrName(ArgumentMatchers.anyString()))
                .thenThrow(new PokemonDetailsException("Exception while retrieving pokemon details"));

        //WHEN
        final ResultActions resultActions = mockMvc.perform(
                get("/pokemon/pikachu")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //THEN
        resultActions.andExpect(status().is5xxServerError());
    }

    @Test
    public void shouldReturnValidationErrorForBlankPokemonDetails() throws Exception {
        // GIVEN
        when(pokemonDetailsService.getByIdOrName(ArgumentMatchers.anyString()))
                .thenThrow(new PokemonDetailsException("Exception while retrieving pokemon details"));

        //WHEN
        final ResultActions resultActions = mockMvc.perform(
                get("/pokemon/")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //THEN
        resultActions.andExpect(status().is4xxClientError());
    }
}
