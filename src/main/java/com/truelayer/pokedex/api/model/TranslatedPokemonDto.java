package com.truelayer.pokedex.api.model;

import lombok.Builder;

import java.util.Objects;

@Builder
public class TranslatedPokemonDto {
    private final String name;
    private final String description;
    private final String habitat;
    private final String translation;
    private final Boolean isTranslated;
    private final Boolean isLegendary;

    public TranslatedPokemonDto(
            final String name,
            final String description,
            final String habitat,
            final String translation,
            final Boolean isTranslated,
            final Boolean isLegendary
    ) {
        this.name = name;
        this.description = description;
        this.habitat = habitat;
        this.translation = translation;
        this.isTranslated = isTranslated;
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

    public String getTranslation() {
        return translation;
    }

    public Boolean getTranslated() {
        return isTranslated;
    }

    public Boolean getLegendary() {
        return isLegendary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslatedPokemonDto that = (TranslatedPokemonDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(habitat, that.habitat) &&
                Objects.equals(translation, that.translation) &&
                Objects.equals(isTranslated, that.isTranslated) &&
                Objects.equals(isLegendary, that.isLegendary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, habitat, translation, isTranslated, isLegendary);
    }

    @Override
    public String toString() {
        return String.format(
                "TranslatedPokemonDto{name='%s', description='%s', habitat='%s', translation='%s', " +
                        "isTranslated=%s, isLegendary=%s}",
                name, description, habitat, translation, isTranslated, isLegendary
        );
    }
}
