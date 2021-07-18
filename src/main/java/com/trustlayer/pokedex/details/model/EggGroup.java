package com.trustlayer.pokedex.details.model;

import java.util.Objects;

public class EggGroup{
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
        EggGroup eggGroup = (EggGroup) o;
        return Objects.equals(name, eggGroup.name) && Objects.equals(url, eggGroup.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return String.format("EggGroup{name='%s', url='%s'}", name, url);
    }
}
