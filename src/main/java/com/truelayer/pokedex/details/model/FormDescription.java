package com.truelayer.pokedex.details.model;

import java.util.Objects;

public class FormDescription{
    public String description;
    public Language language;

    public String getDescription() {
        return description;
    }

    public Language getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormDescription that = (FormDescription) o;
        return Objects.equals(description, that.description) && Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, language);
    }

    @Override
    public String toString() {
        return String.format("FormDescription{description='%s', language=%s}", description, language);
    }
}
