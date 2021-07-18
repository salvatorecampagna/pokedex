package com.truelayer.pokedex.details.model;

import java.util.Objects;

public class Pokedex{
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
        Pokedex pokedex = (Pokedex) o;
        return Objects.equals(name, pokedex.name) && Objects.equals(url, pokedex.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return String.format("Pokedex{name='%s', url='%s'}", name, url);
    }
}
