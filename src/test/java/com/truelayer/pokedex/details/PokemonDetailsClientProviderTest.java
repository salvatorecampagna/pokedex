package com.truelayer.pokedex.details;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PokemonDetailsClientProviderTest {
    @Autowired
    private PokemonDetailsClientProvider pokemonDetailsClientProvider;

    @Test
    public void shouldProvidePokemonDetailsCLient() {
        final PokemonDetailsClient pokemonDetailsClient = pokemonDetailsClientProvider.get();

        assertThat(pokemonDetailsClient).isInstanceOf(PokemonDetailsClientImpl.class);
    }
}