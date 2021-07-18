package com.trustlayer.pokedex.details;

import com.trustlayer.pokedex.details.model.PokemonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonDetailsServiceImpl implements PokemonDetailsService {

    private static final String URL = "https://pokeapi.co/api/v2/pokemon-species/%s";

    private final RestTemplate restTemplate;

    public PokemonDetailsServiceImpl(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public PokemonDetails getByIdOrName(final String idOrName) {
        final String url = String.format(URL, idOrName);
        final ResponseEntity<PokemonDetails> response = restTemplate.getForEntity(url, PokemonDetails.class);
        final HttpStatus statusCode = response.getStatusCode();
        if (!statusCode.is2xxSuccessful()) {
            throw new PokemonDetailsException(
                    String.format("Error while invoking the pokemon details service. Status code: %s", statusCode)
            );
        }

        return response.getBody();
    }
}
