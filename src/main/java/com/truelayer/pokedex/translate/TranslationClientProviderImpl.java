package com.truelayer.pokedex.translate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

@Component
public class TranslationClientProviderImpl implements TranslationClientProvider {
    private static final String CAVE_HABITAT = "cave";
    private final RestTemplateBuilder restTemplateBuilder;

    public TranslationClientProviderImpl(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public TranslationClient get(
            final String habitat,
            final Boolean isLegendary
    ) {
        if (CAVE_HABITAT.equalsIgnoreCase(habitat) && isLegendary) {
            return new TranslationClientImpl(restTemplateBuilder.build(), "shakespeare");
        }
        return new TranslationClientImpl(restTemplateBuilder.build(), "yoda");
    }
}
