package com.truelayer.pokedex.translate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.truelayer.pokedex.translate.model.TranslatedPokemon;
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
    public TranslatedPokemon translate(final TranslatedPokemon translatedPokemon) {

        try {
            final TranslationClient client = translationClientProvider.get(
                    translatedPokemon.getHabitat(),
                    translatedPokemon.getLegendary()
            );
            final String translatedDescription = client.translate(translatedPokemon.getDescription());
            logger.info(
                    String.format(
                            "Translation, original text: '%s', translated text: '%s'",
                            translatedPokemon.getDescription(),
                            translatedDescription
                    )

            );
            return new TranslatedPokemon(
                    translatedPokemon.getName(),
                    translatedDescription,
                    translatedPokemon.getHabitat(),
                    translatedPokemon.getLegendary()
            );
        } catch(RestClientException e) {
            logger.error(String.format("Error while invoking the translation rest api: '%s'", e.getMessage()));
            return translatedPokemon;
        }
    }

    public TranslatedPokemon translateFallback(final TranslatedPokemon translatedPokemon) {
        logger.info("Fallback - Returning original description");
        return translatedPokemon;
    }
}
