package com.trustlayer.pokedex.details;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.trustlayer.pokedex.details.model.PokemonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @HystrixCommand(
            fallbackMethod = "getByIdOrNameFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public PokemonDetails getByIdOrName(String idOrName) {
        final String url = String.format(props.getUrl(), idOrName);
        final ResponseEntity<PokemonDetails> response = restTemplate.getForEntity(url, PokemonDetails.class);
        final HttpStatus statusCode = response.getStatusCode();
        if (!statusCode.is2xxSuccessful()) {
            throw new PokemonDetailsException(
                    String.format("Error while invoking the pokemon details service. Status code: %s", statusCode)
            );
        }

        return response.getBody();
    }

    public PokemonDetails getByIdOrNameFallback(String idOrName) {
        throw new PokemonDetailsException(
                String.format("Error while invoking the pokemon details service for pokemon '%s'", idOrName)
        );
    }
}
