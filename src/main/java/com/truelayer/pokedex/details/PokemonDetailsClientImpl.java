package com.truelayer.pokedex.details;

import com.truelayer.pokedex.details.model.PokemonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokemonDetailsClientImpl implements PokemonDetailsClient {
    private final RestTemplate restTemplate;

    @Autowired
    private PokemonDetailsProps props;

    public PokemonDetailsClientImpl(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override

    public PokemonDetails getByIdOrName(final String idOrName) {
            final String url = String.format(props.getUrl(), idOrName);
            return restTemplate.getForEntity(url, PokemonDetails.class).getBody();
    }
}
