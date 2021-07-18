package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.details.model.Pokemon;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import com.truelayer.pokedex.translate.model.Translation;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.truelayer.pokedex.TestUtils.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TranslationServiceTest {

    @MockBean
    private TranslationClientProvider translationClientProvider;

    @Autowired
    private TranslationService translationService;


    @Test
    public void shouldReturnPokemonDetails() {
        //GIVEN
        final String description = "Pikachu is one of the most popular Pokemons";
        final Pokemon pokemon = buildPokemon(
                "Pikachu",
                description,
                "The forest",
                false
        );
        when(translationClientProvider.get(ArgumentMatchers.eq(pokemon))).thenReturn(
                new TranslationClient() {
                    @Override
                    public Translation translate(String text) {
                        return new Translation(text.toUpperCase(), text, "uppercase");
                    }

                    @Override
                    public String getTranslation() {
                        return "uppercase";
                    }
                }
        );

        //WHEN
        final TranslatedPokemon translatedPokemonResponse = translationService.translate(pokemon);

        // THEN
        assertThat(translatedPokemonResponse.getDescription()).isEqualTo(description.toUpperCase());
    }

    @Test
    public void circuitBreakerFallbackShouldReturnOriginalTranslatedPokemon() {
        //GIVEN
        final String description = "Pikachu is one of the most popular Pokemons";
        final Pokemon pokemon = buildPokemon(
                "Pikachu",
                description,
                "The forest",
                false
        );

        //WHEN
        final TranslatedPokemon response = new TranslationServiceImpl().translateFallback(pokemon);

        //THEN
        assertThat(response).isEqualTo(
                new TranslatedPokemon(
                        pokemon.getName(),
                        pokemon.getDescription(),
                        pokemon.getHabitat(),
                        null,
                        false,
                        pokemon.getLegendary()
                )
        );
    }

}
