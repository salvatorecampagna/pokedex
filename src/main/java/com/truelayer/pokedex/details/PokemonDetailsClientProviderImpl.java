package com.truelayer.pokedex.details;

import com.truelayer.pokedex.translate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

@Component
public class PokemonDetailsClientProviderImpl implements PokemonDetailsClientProvider {
    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private PokemonDetailsProps props;

    private final Logger logger = LoggerFactory.getLogger(TranslationClientProviderImpl.class);

    public PokemonDetailsClientProviderImpl(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public PokemonDetailsClient get() {
        return new PokemonDetailsClientImpl(restTemplateBuilder.build(), props);
    }
}
