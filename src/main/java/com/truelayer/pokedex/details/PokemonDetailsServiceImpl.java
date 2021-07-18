package com.truelayer.pokedex.details;

import com.truelayer.pokedex.details.model.PokemonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonDetailsServiceImpl implements PokemonDetailsService {

    @Autowired
    private PokemonDetailsClient pokemonDetailsClient;

    @Override
    public PokemonDetails getByIdOrName(final String idOrName) {
        return pokemonDetailsClient.getByIdOrName(idOrName);
    }

}
