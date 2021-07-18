package com.truelayer.pokedex;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.details.model.FormDescription;
import com.truelayer.pokedex.details.model.Habitat;
import com.truelayer.pokedex.details.model.Language;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;

import java.util.Collections;

public class TestUtils {

    private TestUtils() {}

    public static TranslatedPokemonDto buildTranslatedPokemonDto(
            final TranslatedPokemon translatedPokemonResponse
    ) {
        return new TranslatedPokemonDto(
                translatedPokemonResponse.getName(),
                translatedPokemonResponse.getDescription(),
                translatedPokemonResponse.getHabitat(),
                translatedPokemonResponse.getLegendary()
        );
    }

    public static PokemonDetails buildPokemonDetails(
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

    public static PokemonDto buildPokemonDto(
            final PokemonDetails pokemonDetails
    ) {
        return PokemonDto.builder()
                .name(pokemonDetails.getName())
                .description(pokemonDetails.getFormDescriptions().get(0).getDescription())
                .habitat(pokemonDetails.getHabitat().getName())
                .isLegendary(pokemonDetails.isLegendary())
                .build();
    }

    public static TranslatedPokemon buildTranslatedPokemon(
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

    public static void waitHystrixCircuitBreakerOpens() throws InterruptedException {
        Thread.sleep(5000);
    }

    public static HystrixCircuitBreaker getCircuitBreaker(final String name) {
        return HystrixCircuitBreaker.Factory.getInstance(
                HystrixCommandKey.Factory.asKey(name)
        );
    }
}
