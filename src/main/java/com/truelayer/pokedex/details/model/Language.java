package com.truelayer.pokedex.details.model;

import lombok.Builder;

import java.util.Objects;

@Builder
public class Language{
    public String name;
    public String url;

    public Language(String name, String url) {
        this.name = name;
        this.url = url;
    }

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
        Language language = (Language) o;
        return Objects.equals(name, language.name) && Objects.equals(url, language.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return String.format("Language{name='%s', url='%s'}", name, url);
    }
}
