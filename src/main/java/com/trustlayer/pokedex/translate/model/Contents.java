package com.trustlayer.pokedex.translate.model;

import java.util.Objects;

public class Contents {
    private String translated;
    private String text;
    private String translation;

    public Contents() {
    }

    public String getTranslated() {
        return translated;
    }

    public String getText() {
        return text;
    }

    public String getTranslation() {
        return translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contents that = (Contents) o;
        return Objects.equals(translated, that.translated) &&
                Objects.equals(text, that.text) &&
                Objects.equals(translation, that.translation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(translated, text, translation);
    }

    @Override
    public String toString() {
        return String.format(
                "Contents{translated='%s', text='%s', translation='%s'}",
                translated, text, translation
        );
    }
}
