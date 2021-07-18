package com.trustlayer.pokedex.details.model;

import java.util.Objects;

public class Name{
    public String name;
    public Language language;

    public String getName() {
        return name;
    }

    public Language getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name) && Objects.equals(language, name1.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, language);
    }

    @Override
    public String toString() {
        return String.format("Name{name='%s', language=%s}", name, language);
    }
}
