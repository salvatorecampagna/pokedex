package com.trustlayer.pokedex.details.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Variety{
    @JsonProperty("is_default")
    public boolean isDefault;
    public Pokemon pokemon;

    public boolean isDefault() {
        return isDefault;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variety variety = (Variety) o;
        return isDefault == variety.isDefault && Objects.equals(pokemon, variety.pokemon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDefault, pokemon);
    }

    @Override
    public String toString() {
        return String.format("Variety{isDefault=%s, pokemon=%s}", isDefault, pokemon);
    }
}
