package com.truelayer.pokedex.details;

import com.truelayer.pokedex.details.model.PokemonDetails;
import org.springframework.web.client.RestTemplate;

public class PokemonDetailsClientImpl implements PokemonDetailsClient {
    private final RestTemplate restTemplate;
    private final PokemonDetailsProps props;

    public PokemonDetailsClientImpl(
            final RestTemplate restTemplate,
            final PokemonDetailsProps props
    ) {
        this.restTemplate = restTemplate;
        this.props = props;
    }

    @Override

    public PokemonDetails getByIdOrName(final String idOrName) {
        final String url = String.format(props.getUrl(), idOrName);
        return restTemplate.getForObject(url, PokemonDetails.class);
    }
}
