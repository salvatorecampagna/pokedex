package com.truelayer.pokedex.details.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class FlavorTextEntry{
    @JsonProperty("flavor_text")
    public String flavorText;
    public Language language;
    public Version version;

    public String getFlavorText() {
        return flavorText;
    }

    public Language getLanguage() {
        return language;
    }

    public Version getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlavorTextEntry that = (FlavorTextEntry) o;
        return Objects.equals(flavorText, that.flavorText) &&
                Objects.equals(language, that.language) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flavorText, language, version);
    }

    @Override
    public String toString() {
        return String.format(
                "FlavorTextEntry{flavorText='%s', language=%s, version=%s}",
                flavorText, language, version
        );
    }
}
