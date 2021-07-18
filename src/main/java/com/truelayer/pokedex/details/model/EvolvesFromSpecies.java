package com.truelayer.pokedex.details.model;

import java.util.Objects;

public class EvolvesFromSpecies{
    public String name;
    public String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvolvesFromSpecies that = (EvolvesFromSpecies) o;
        return Objects.equals(name, that.name) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return String.format("EvolvesFromSpecies{name='%s', url='%s'}", name, url);
    }
}
