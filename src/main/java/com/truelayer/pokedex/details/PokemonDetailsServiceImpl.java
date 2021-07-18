package com.truelayer.pokedex.details;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.truelayer.pokedex.details.model.PokemonDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class PokemonDetailsServiceImpl implements PokemonDetailsService {

    @Autowired
    private PokemonDetailsClient pokemonDetailsClient;

    private final Logger logger = LoggerFactory.getLogger(PokemonDetailsServiceImpl.class);

    @Override
    @HystrixCommand(
            threadPoolKey = "pokemon-details",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    public PokemonDetails getByIdOrName(final String idOrName) {
        try {
            return pokemonDetailsClient.getByIdOrName(idOrName);
        } catch(RestClientException e) {
            final String message = String.format("Error while invoking the pokemon rest api: '%s'", e.getMessage());
            logger.error(message);
            throw new PokemonDetailsException(message, e);
        }
    }

}
