package com.truelayer.pokedex;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.details.model.*;
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
            final String description,
            final String habitat,
            boolean isLegendary
    ) {
        return PokemonDetails.builder()
                .id(id)
                .name(name)
                .habitat(Habitat.builder().id(0).name(habitat).names(Collections.emptyList()).build())
                .flavorTextEntries(
                        Collections.singletonList(
                                FlavorTextEntry.builder()
                                        .flavorText(description)
                                        .version(Version.builder().name("v1").url("http://v1.org").build())
                                        .language(Language.builder().name("en").url("http://en.org").build()).
                                        build())

                )
                .isLegendary(isLegendary)
                .build();
    }

    public static PokemonDto buildPokemonDto(
            final Pokemon pokemon
    ) {
        return new PokemonDto(
                pokemon.getName(),
                pokemon.getDescription(),
                pokemon.getHabitat(),
                pokemon.getLegendary()
        );
    }

    public static Pokemon buildPokemon(
            final String name,
            final String description,
            final String habitat,
            final Boolean isLegendary
    ) {
        return new Pokemon(
                name,
                description,
                habitat,
                isLegendary
        );
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
