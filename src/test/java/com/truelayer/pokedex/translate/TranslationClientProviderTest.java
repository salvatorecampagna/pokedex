package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.details.model.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TranslationClientProviderTest {
    @Autowired
    private TranslationClientProvider translationClientProvider;

    @Test
    public void shouldReturnYodaTranslationClientWhenHabitatCave() {
        //GIVEN
        final Pokemon pokemon = new Pokemon(
                "Pikachu",
                "A popular pokemon",
                "cave",
                false
        );

        //WHEN
        final TranslationClient translationClient = translationClientProvider.get(pokemon);

        //THEN
        assertThat("yoda").isEqualTo(translationClient.getTranslation());
    }

    @Test
    public void shouldReturnYodaTranslationClientWhenLegenday() {
        //GIVEN
        final Pokemon pokemon = new Pokemon(
                "Pikachu",
                "A popular pokemon",
                "the forest",
                true
        );

        //WHEN
        final TranslationClient translationClient = translationClientProvider.get(pokemon);

        //THEN
        assertThat("yoda").isEqualTo(translationClient.getTranslation());
    }

    @Test
    public void shouldReturnShakespeareTranslationClient() {
        //GIVEN
        final Pokemon pokemon = new Pokemon(
                "Pikachu",
                "A popular pokemon",
                "the forest",
                false
        );

        //WHEN
        final TranslationClient translationClient = translationClientProvider.get(pokemon);

        //THEN
        assertThat("shakespeare").isEqualTo(translationClient.getTranslation());
    }
}