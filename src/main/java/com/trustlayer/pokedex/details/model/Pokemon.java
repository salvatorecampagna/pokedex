package com.trustlayer.pokedex.details.model;

import java.util.Objects;

public class Pokemon {
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
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(name, pokemon.name) && Objects.equals(url, pokemon.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return String.format("Pokemon{name='%s', url='%s'}", name, url);
    }
}
