package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.details.model.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

@Component
public class TranslationClientProviderImpl implements TranslationClientProvider {
    private static final String CAVE_HABITAT = "cave";
    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private TranslateProps props;

    private final Logger logger = LoggerFactory.getLogger(TranslationClientProviderImpl.class);

    public TranslationClientProviderImpl(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public TranslationClient get(
            final Pokemon pokemon
    ) {
        final String translation = CAVE_HABITAT.equalsIgnoreCase(pokemon.getHabitat()) || pokemon.getLegendary() ?
                "shakespeare" : "yoda";
        logger.info(
                String.format(
                        "Translation: %s, habitat: %s, isLegendary: %s",
                        translation, pokemon.getHabitat(), pokemon.getLegendary()
                )
        );
        return new TranslationClientImpl(props, restTemplateBuilder.build(), translation);
    }
}
