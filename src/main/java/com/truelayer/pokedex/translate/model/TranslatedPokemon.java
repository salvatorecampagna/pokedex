package com.truelayer.pokedex.translate.model;

import lombok.Builder;

import java.util.Objects;

@Builder
public class TranslatedPokemon {
    private final String name;
    private final String description;
    private final String habitat;
    private final Boolean isLegendary;

    public TranslatedPokemon(
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
        TranslatedPokemon that = (TranslatedPokemon) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(habitat, that.habitat) &&
                Objects.equals(isLegendary, that.isLegendary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, habitat, isLegendary);
    }

    @Override
    public String toString() {
        return String.format(
                "TranslatedPokemon{name='%s', description='%s', habitat='%s', isLegendary=%s}",
                name, description, habitat, isLegendary
        );
    }
}