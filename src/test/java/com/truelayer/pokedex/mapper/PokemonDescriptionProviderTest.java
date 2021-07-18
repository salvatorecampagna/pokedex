package com.truelayer.pokedex.mapper;

import com.truelayer.pokedex.details.model.FlavorTextEntry;
import com.truelayer.pokedex.details.model.Language;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.details.model.Version;
import com.truelayer.pokedex.mapper.PokemonDescriptionProvider;
import com.truelayer.pokedex.mapper.PokemonDescriptionProviderImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PokemonDescriptionProviderTest {

    private final PokemonDescriptionProvider provider = new PokemonDescriptionProviderImpl();

    @Test
    public void shouldReturnFirstEnglishDescription() {
        //GIVEN
        final String expectedDescription = "Second description";

        //WHEN
        final String description = provider.get(
                PokemonDetails.builder().flavorTextEntries(
                        Arrays.asList(
                                FlavorTextEntry.builder()
                                        .flavorText("Prima descrizione")
                                        .language(Language.builder().name("it").url("http://it.org").build())
                                        .version(Version.builder().name("it-v1").url("http://version.it.org").build())
                                        .build(),
                                FlavorTextEntry.builder()
                                        .flavorText(expectedDescription)
                                        .language(Language.builder().name("en").url("http://en.org").build())
                                        .version(Version.builder().name("en-v1").url("http://version.en.org").build())
                                        .build(),
                                FlavorTextEntry.builder()
                                        .flavorText("Third description")
                                        .language(Language.builder().name("en").url("http://en.org").build())
                                        .version(Version.builder().name("en-v1").url("http://version.en.org").build())
                                        .build()
                        )
                ).build()
        );

        //THEN
        assertThat(description).isEqualTo(expectedDescription);
    }

    @Test
    public void shouldThrowNoSuchElementExceptionIfNoEnglishDescption() {

        //WHEN
        try {
            provider.get(
                    PokemonDetails.builder().flavorTextEntries(
                            Arrays.asList(
                                    FlavorTextEntry.builder()
                                            .flavorText("Prima descrizione")
                                            .language(Language.builder().name("it").url("http://it.org").build())
                                            .version(Version.builder().name("it-v1").url("http://version.it.org").build())
                                            .build(),
                                    FlavorTextEntry.builder()
                                            .flavorText("Primera description")
                                            .language(Language.builder().name("es").url("http://en.org").build())
                                            .version(Version.builder().name("en-v1").url("http://version.en.org").build())
                                            .build()
                            )
                    ).build()
            );
        } catch (NoSuchElementException e) {
            assertThat(e.getMessage()).isEqualTo("No description found");
        }
    }

    @Test
    public void shouldThrowNoSuchElementExceptionIfEMptyFlavorTextEntries() {

        //WHEN
        try {
            provider.get(
                    PokemonDetails.builder().flavorTextEntries(Collections.emptyList()).build()
            );
        } catch (NoSuchElementException e) {
            assertThat(e.getMessage()).isEqualTo("No description found");
        }
    }
}
