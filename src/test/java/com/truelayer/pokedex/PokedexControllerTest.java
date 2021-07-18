package com.truelayer.pokedex;

import com.truelayer.pokedex.api.PokemonController;
import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.details.PokemonDetailsService;
import com.truelayer.pokedex.details.model.FormDescription;
import com.truelayer.pokedex.details.model.Habitat;
import com.truelayer.pokedex.details.model.Language;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.mapper.ObjectMapper;
import com.truelayer.pokedex.translate.TranslationService;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(PokemonController.class)
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
        final PokemonDetails pokemonDetails = buildPokemonDetails(
                1,
                "Pikachu",
                "Dangerous habitat",
                "One of the most popular Pokemons",
                false
        );
        final PokemonDto pokemonDto = buildPokemonDto(pokemonDetails);

        when(pokemonDetailsService.getByIdOrName(ArgumentMatchers.matches(pokemonName)))
                .thenReturn(pokemonDetails);
        when(objectMapper.toPokemonDto(ArgumentMatchers.eq(pokemonDetails)))
                .thenReturn(pokemonDto);

        //WHEN
        final ResultActions resultActions = mockMvc.perform(
                get(String.format("/pokemon/%s", pokemonName))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //THEN
        resultActions.andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(pokemonDto)));
    }

    @Test
    public void shouldReturnTranslatedPokemon() throws Exception {
        // GIVEN
        final String pokemonName = "bulbasaur";
        final PokemonDetails pokemonDetails = buildPokemonDetails(
                2,
                "Balbasaur",
                "The forest",
                "Another Pokemon that I heard of",
                true
        );
        final TranslatedPokemon translatedPokemonRequest = buildTranslatedPokemon(
                pokemonDetails.getName(),
                pokemonDetails.getFormDescriptions().get(0).getDescription(),
                pokemonDetails.getHabitat().getName(),
                pokemonDetails.isLegendary()
        );
        final TranslatedPokemon translatedPokemonResponse = buildTranslatedPokemon(
                translatedPokemonRequest.getName(),
                translatedPokemonRequest.getDescription().toLowerCase(),
                translatedPokemonRequest.getHabitat(),
                translatedPokemonRequest.getLegendary()
        );
        final TranslatedPokemonDto translatedPokemonDto = buildTranslatedPokemonDto(translatedPokemonResponse);

        when(pokemonDetailsService.getByIdOrName(ArgumentMatchers.matches(pokemonName)))
                .thenReturn(pokemonDetails);
        when(objectMapper.toTranslatedPokemon(ArgumentMatchers.eq(pokemonDetails)))
                .thenReturn(translatedPokemonRequest);
        when(translationService.translate(translatedPokemonRequest))
            .thenReturn(translatedPokemonResponse);
        when(objectMapper.toTranslatedPokemonDto(ArgumentMatchers.eq(translatedPokemonResponse)))
                .thenReturn(translatedPokemonDto);

        //WHEN
        final ResultActions resultActions = mockMvc.perform(
                get(String.format("/pokemon/translated/%s", pokemonName))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //THEN
        resultActions.andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(translatedPokemonDto)));
    }

    private TranslatedPokemonDto buildTranslatedPokemonDto(
            final TranslatedPokemon translatedPokemonResponse
    ) {
        return new TranslatedPokemonDto(
                translatedPokemonResponse.getName(),
                translatedPokemonResponse.getDescription(),
                translatedPokemonResponse.getHabitat(),
                translatedPokemonResponse.getLegendary()
        );
    }

    private PokemonDetails buildPokemonDetails(
            int id,
            final String name,
            final String habitat,
            final String description,
            boolean isLegendary
    ) {
        return PokemonDetails.builder()
                .id(id)
                .name(name)
                .habitat(Habitat.builder().id(0).name(habitat).names(Collections.emptyList()).build())
                .formDescriptions(
                        Collections.singletonList(new FormDescription(description, new Language("en", "en")))
                )
                .isLegendary(isLegendary)
                .build();
    }

    private PokemonDto buildPokemonDto(
            final PokemonDetails pokemonDetails
    ) {
        return PokemonDto.builder()
                .name(pokemonDetails.getName())
                .description(pokemonDetails.getFormDescriptions().get(0).getDescription())
                .habitat(pokemonDetails.getHabitat().getName())
                .isLegendary(pokemonDetails.isLegendary())
                .build();
    }

    private TranslatedPokemon buildTranslatedPokemon(
            final String name,
            final String description,
            final String habitat,
            boolean isLegendary

    ) {
        return TranslatedPokemon.builder()
                .name(name)
                .description(description)
                .habitat(habitat)
                .isLegendary(isLegendary)
                .build();
    }
}
