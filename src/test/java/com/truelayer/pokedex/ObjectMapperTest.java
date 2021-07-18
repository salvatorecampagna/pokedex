package com.truelayer.pokedex;

import com.truelayer.pokedex.api.model.PokemonDto;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.mapper.ObjectMapper;
import com.truelayer.pokedex.mapper.ObjectMapperImpl;
import com.truelayer.pokedex.mapper.PokemonDescriptionProvider;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.truelayer.pokedex.TestUtils.buildPokemonDetails;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectMapperTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnPokemonDto() {
        final PokemonDetails pokemonDetails = buildPokemonDetails(
                1, "Bulbasaur", "The forest", "Another pokemon living in the forest", false
        );
        final PokemonDto pokemonDto = objectMapper.toPokemonDto(
                pokemonDetails
        );

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
    public void shouldReturnTranslatedPokemon() {
    }

    @Test
    public void shouldReturnTranslatedPokeonDto() {

    }
}
