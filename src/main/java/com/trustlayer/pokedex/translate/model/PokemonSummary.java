package com.trustlayer.pokedex.translate.model;

import java.util.Objects;

public class PokemonSummary {
    private final String name;
    private final String habitat;
    private final String description;
    private final Boolean isLegendary;

    public PokemonSummary(
            final String name,
            final String habitat,
            final String description,
            final Boolean isLegendary
    ) {
        this.name = name;
        this.habitat = habitat;
        this.description = description;
        this.isLegendary = isLegendary;
    }

    public String getName() {
        return name;
    }

    public String getHabitat() {
        return habitat;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getLegendary() {
        return isLegendary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonSummary that = (PokemonSummary) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(habitat, that.habitat) &&
                Objects.equals(description, that.description) &&
                Objects.equals(isLegendary, that.isLegendary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, habitat, description, isLegendary);
    }

    @Override
    public String toString() {
        return String.format(
                "Pokemon{name='%s', habitat='%s', Description='%s', isLegendary=%s}",
                name, habitat, description, isLegendary
        );
    }
}
