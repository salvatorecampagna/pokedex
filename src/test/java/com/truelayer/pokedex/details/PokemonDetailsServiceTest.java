package com.truelayer.pokedex.details;

import com.truelayer.pokedex.TestUtils;
import com.truelayer.pokedex.details.model.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class PokemonDetailsServiceTest {

    @MockBean
    private PokemonDetailsClientProvider pokemonDetailsClientProvider;

    @Autowired
    private PokemonDetailsService pokemonDetailsService;


    @Test
    public void shouldReturnPokemonDetails() {
        //GIVEN
        final String pokemonName = "pikachu";
        when(pokemonDetailsClientProvider.get()).thenReturn(
                idOrName -> TestUtils.buildPokemonDetails(
                        1,
                        "Pikachu",
                        "One of the most popular Pokemons",
                        "The forest",
                        false
                )
        );

        //WHEN
        final Pokemon pokemon = pokemonDetailsService.getByIdOrName(pokemonName);

        // THEN
        assertThat(pokemon).isEqualTo(TestUtils.buildPokemon(
                "Pikachu",
                "One of the most popular Pokemons",
                "The forest",
                false
        ));
    }
}