package com.truelayer.pokedex.translate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.truelayer.pokedex.details.model.Pokemon;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;
import com.truelayer.pokedex.translate.model.Translation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private TranslationClientProvider translationClientProvider;

    private final Logger logger = LoggerFactory.getLogger(TranslationServiceImpl.class);

    @Override
    @HystrixCommand(
            fallbackMethod = "translateFallback",
            threadPoolKey = "pokemon-translate",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public TranslatedPokemon translate(final Pokemon pokemon) {

        try {
            final TranslationClient client = translationClientProvider.get(pokemon);
            final Translation translation = client.translate(pokemon.getDescription());
            logger.info(
                    String.format(
                            "Translation, original text: '%s', translated text: '%s', translation: '%s",
                            translation.getText(),
                            translation.getTranslated(),
                            translation.getTranslation()
                    )

            );
            return new TranslatedPokemon(
                    pokemon.getName(),
                    translation.getTranslated(),
                    pokemon.getHabitat(),
                    translation.getTranslation(),
                    true,
                    pokemon.getLegendary()
            );
        } catch(RestClientException e) {
            logger.error(String.format("Error while invoking the translation rest api: '%s'", e.getMessage()));
            return new TranslatedPokemon(
                    pokemon.getName(),
                    pokemon.getDescription(),
                    pokemon.getHabitat(),
                    null,
                    false,
                    pokemon.getLegendary()
            );
        }
    }

    public TranslatedPokemon translateFallback(final Pokemon pokemon) {
        logger.info("Fallback - Returning original description");
        return new TranslatedPokemon(
                pokemon.getName(),
                pokemon.getDescription(),
                pokemon.getHabitat(),
                null,
                false,
                pokemon.getLegendary()
        );
    }
}
