package com.truelayer.pokedex.details.model;

import java.util.Objects;

public class Pokemon {
    private final String name;
    private final String description;
    private final String habitat;
    private final Boolean isLegendary;

    public Pokemon(
            final String name,
            final String description,
            final String habitat,
            final Boolean isLegendary
    ) {
        this.name = name;
        this.description = description;
        this.habitat = habitat;
        this.isLegendary = isLegendary;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHabitat() {
        return habitat;
    }

    public Boolean getLegendary() {
        return isLegendary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(name, pokemon.name) &&
                Objects.equals(description, pokemon.description) &&
                Objects.equals(habitat, pokemon.habitat) &&
                Objects.equals(isLegendary, pokemon.isLegendary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, habitat, isLegendary);
    }

    @Override
    public String toString() {
        return String.format(
                "Pokemon{name='%s', description='%s', habitat='%s', isLegendary=%s}",
                name, description, habitat, isLegendary
        );
    }
}
