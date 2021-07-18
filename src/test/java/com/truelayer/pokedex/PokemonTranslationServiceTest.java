package com.truelayer.pokedex;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixCircuitBreaker;
import com.truelayer.pokedex.translate.TranslationClientProvider;
import com.truelayer.pokedex.translate.TranslationService;
import com.truelayer.pokedex.translate.TranslationServiceImpl;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import static com.truelayer.pokedex.TestUtils.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PokemonTranslationServiceTest {

    private static final String HYSTRIX_CIRCUIT_BREAKER_NAME = "translate";

    @MockBean
    private TranslationClientProvider translationClientProvider;

    @Autowired
    private TranslationService translationService;

    @Before
    public void setup() {
        Hystrix.reset();
        ConfigurationManager
                .getConfigInstance()
                .setProperty("hystrix.command.translate.circuitBreaker.requestVolumeThreshold", 1);
    }


    @Test
    public void shouldReturnPokemonDetails() {
        //GIVEN
        when(translationClientProvider.get(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean())
        ).thenReturn(String::toUpperCase);

        final String description = "Pikachu is one of the most popular Pokemons";
        final TranslatedPokemon request = buildTranslatedPokemon(
                "Pikachu",
                description,
                "The forest",
                false
        );

        //WHEN
        final TranslatedPokemon translatedPokemonResponse = translationService.translate(request);

        // THEN
        assertThat(translatedPokemonResponse.getDescription()).isEqualTo(description.toUpperCase());
    }

    @Test
    public void shouldReturnNonTransltedDescriptionOnException() {
        //GIVEN
        when(translationClientProvider.get(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean())
        ).thenThrow(new RestClientException("Error while calling rest api"));

        final String description = "Pikachu is one of the most popular Pokemons";
        final TranslatedPokemon request = buildTranslatedPokemon(
                "Pikachu",
                description,
                "The forest",
                false
        );


        //WHEN
        final TranslatedPokemon translatedPokemonResponse = translationService.translate(request);

        // THEN
        assertThat(translatedPokemonResponse.getDescription()).isEqualTo(description);
    }

    @Test
    public void circuitBreakerShouldBeOpenOnSuccess() {
        //GIVEN
        when(translationClientProvider.get(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean())
        ).thenReturn(String::toUpperCase);

        final String description = "Pikachu is one of the most popular Pokemons";
        final TranslatedPokemon request = buildTranslatedPokemon(
                "Pikachu",
                description,
                "The forest",
                false
        );

        //WHEN
        translationService.translate(request);

        //THEN
        final HystrixCircuitBreaker hystrixCircuitBreaker = getCircuitBreaker(HYSTRIX_CIRCUIT_BREAKER_NAME);
        assertThat(hystrixCircuitBreaker.allowRequest()).isEqualTo(true);
    }

    @Test
    public void circuitBreakerShouldBeClosedOnFailure() throws InterruptedException {
        //GIVEN
        when(translationClientProvider.get(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean())
        ).thenThrow(new RestClientException("Error while calling rest api"));

        //WHEN
        try {
            final String description = "Pikachu is one of the most popular Pokemons";
            final TranslatedPokemon request = buildTranslatedPokemon(
                    "Pikachu",
                    description,
                    "The forest",
                    false
            );
            final TranslatedPokemon translatedPokemonResponse = translationService.translate(request);
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
        final TranslatedPokemon request = buildTranslatedPokemon(
                "Pikachu",
                description,
                "The forest",
                false
        );

        //WHEN
        final TranslatedPokemon response = new TranslationServiceImpl().translateFallback(request);

        //THEN
        assertThat(response).isEqualTo(request);
    }

}
