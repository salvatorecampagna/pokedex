package com.trustlayer.pokedex.details.model;

import java.util.Objects;

public class EvolutionChain{
    public String url;

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvolutionChain that = (EvolutionChain) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return String.format("EvolutionChain{url='%s'}", url);
    }
}
