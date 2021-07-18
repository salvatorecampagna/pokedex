package com.truelayer.pokedex.mapper;

import com.truelayer.pokedex.api.model.PokemonSummaryDto;
import com.truelayer.pokedex.details.model.PokemonDetails;
import com.truelayer.pokedex.translate.model.PokemonSummary;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class ObjectMapperImpl implements ObjectMapper {

    @Override
    public PokemonSummaryDto pokemonDetailsToPokemonSummaryDto(final PokemonSummary pokemonSummary) {
        return new PokemonSummaryDto(
                pokemonSummary.getName(),
                pokemonSummary.getHabitat(),
                pokemonSummary.getDescription(),
                pokemonSummary.getLegendary()
        );
    }

    @Override
    public PokemonSummaryDto pokemonDetailsToPokemonSummaryDto(final PokemonDetails pokemonDetails) {
        return new PokemonSummaryDto(
                pokemonDetails.getName(),
                pokemonDetails.getHabitat().getName(),
                getPokemonDescription(pokemonDetails),
                pokemonDetails.isLegendary()
        );
    }

    @Override
    public PokemonSummary pokemonDetailsToPokemonSummary(final PokemonDetails pokemonDetails) {
        return new PokemonSummary(
                pokemonDetails.getName(),
                pokemonDetails.getHabitat().getName(),
                getPokemonDescription(pokemonDetails),
                pokemonDetails.isLegendary()
        );
    }

    private String getPokemonDescription(final PokemonDetails pokemonDetails) {
        return pokemonDetails.getFlavorTextEntries().stream().filter(
                entry -> "en".equalsIgnoreCase(entry.getLanguage().getName())
        ).findFirst().orElseThrow(() -> new NoSuchElementException("No english description found")).getFlavorText();
    }
}
