package com.truelayer.pokedex.translate;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixCircuitBreaker;
import com.truelayer.pokedex.details.model.Pokemon;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import com.truelayer.pokedex.translate.model.Translation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestClientException;

import static com.truelayer.pokedex.TestUtils.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TranslationServiceTest {

    private static final String HYSTRIX_CIRCUIT_BREAKER_NAME = "translate";

    @MockBean
    private TranslationClientProvider translationClientProvider;

    @Autowired
    private TranslationService translationService;

    @BeforeEach
    public void setup() {
        Hystrix.reset();
        ConfigurationManager
                .getConfigInstance()
                .setProperty("hystrix.command.translate.circuitBreaker.requestVolumeThreshold", 1);
    }


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
    public void shouldReturnNonTransltedDescriptionOnException() {
        //GIVEN
        final String description = "Pikachu is one of the most popular Pokemons";
        final Pokemon pokemon = buildPokemon(
                "Pikachu",
                description,
                "The forest",
                false
        );
        when(translationClientProvider.get(pokemon))
                .thenThrow(new RestClientException("Error while calling rest api"));

        //WHEN
        final TranslatedPokemon translatedPokemonResponse = translationService.translate(pokemon);

        // THEN
        assertThat(translatedPokemonResponse.getDescription()).isEqualTo(description);
    }

    @Test
    public void circuitBreakerShouldBeOpenOnSuccess() {
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
        translationService.translate(pokemon);

        //THEN
        final HystrixCircuitBreaker hystrixCircuitBreaker = getCircuitBreaker(HYSTRIX_CIRCUIT_BREAKER_NAME);
        assertThat(hystrixCircuitBreaker.allowRequest()).isEqualTo(true);
    }

    @Test
    public void circuitBreakerShouldBeClosedOnFailure() throws InterruptedException {
        //GIVEN
        when(translationClientProvider.get(ArgumentMatchers.any())
        ).thenThrow(new RestClientException("Error while calling rest api"));

        //WHEN
        try {
            final String description = "Pikachu is one of the most popular Pokemons";
            final Pokemon pokemon = buildPokemon(
                    "Pikachu",
                    description,
                    "The forest",
                    false
            );
            final TranslatedPokemon translatedPokemonResponse = translationService.translate(pokemon);
        } catch (RestClientException e) {
            // THEN
            waitHystrixCircuitBreakerOpens();
            final HystrixCircuitBreaker hystrixCircuitBreaker = getCircuitBreaker(HYSTRIX_CIRCUIT_BREAKER_NAME);
            assertThat(hystrixCircuitBreaker.allowRequest()).isFalse();
        }
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
