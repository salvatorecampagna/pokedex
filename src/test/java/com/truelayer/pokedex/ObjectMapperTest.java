package com.truelayer.pokedex;

import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.api.model.TranslatedPokemonDto;
import com.truelayer.pokedex.details.model.Pokemon;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.mapper.ObjectMapper;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.truelayer.pokedex.TestUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectMapperTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnPokemonDto() {
        final Pokemon pokemon = buildPokemon(
                "Bulbasaur", "Another pokemon living in the forest", "The forest", false
        );
        final PokemonDto pokemonDto = objectMapper.toPokemonDto(pokemon);

        assertThat(pokemonDto).isEqualTo(
                new PokemonDto(
                        "Bulbasaur",
                        "Another pokemon living in the forest",
                        "The forest",
                        false
                )
        );

    }

    @Test
    public void shouldReturnTranslatedPokeonDto() {
        final TranslatedPokemon translatedPokemon = buildTranslatedPokemon(
                "Bulbasaur", "Another pokemon living in the forest", "The forest", false
        );
        final TranslatedPokemonDto translatedPokemonDto = objectMapper.toTranslatedPokemonDto(
                translatedPokemon
        );

        assertThat(translatedPokemonDto).isEqualTo(
                new TranslatedPokemonDto(
                        "Bulbasaur",
                        "Another pokemon living in the forest",
                        "The forest",
                        false
                )
        );
    }
}
