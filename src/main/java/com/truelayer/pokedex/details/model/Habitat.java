package com.truelayer.pokedex.details.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.Objects;

@Builder
public class Habitat {
    public int id;
    public String name;
    public List<Name> names;
    @JsonProperty("pokemon_species")
    public List<PokemonSpecy> pokemonSpecies;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Name> getNames() {
        return names;
    }

    public List<PokemonSpecy> getPokemonSpecies() {
        return pokemonSpecies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitat habitat = (Habitat) o;
        return id == habitat.id && Objects.equals(name, habitat.name) && Objects.equals(names, habitat.names) && Objects.equals(pokemonSpecies, habitat.pokemonSpecies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, names, pokemonSpecies);
    }

    @Override
    public String toString() {
        return String.format(
                "Habitat{id=%d, name='%s', names=%s, pokemon_species=%s}",
                id, name, names, pokemonSpecies
        );
    }
}
