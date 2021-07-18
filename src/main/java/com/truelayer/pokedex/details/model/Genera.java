package com.truelayer.pokedex.details.model;

import java.util.Objects;

public class Genera{
    public String genus;
    public Language language;

    public String getGenus() {
        return genus;
    }

    public Language getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genera genera = (Genera) o;
        return Objects.equals(genus, genera.genus) && Objects.equals(language, genera.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genus, language);
    }

    @Override
    public String toString() {
        return String.format("Genera{genus='%s', language=%s}", genus, language);
    }
}
