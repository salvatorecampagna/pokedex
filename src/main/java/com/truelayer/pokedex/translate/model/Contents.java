package com.truelayer.pokedex.translate.model;

import lombok.Builder;

import java.util.Objects;

@Builder
public class Contents {
    private String translated;
    private String text;
    private String translation;

    public Contents() {
    }

    public Contents(
            final String translated,
            final String text,
            final String translation
    ) {
        this.translated = translated;
        this.text = text;
        this.translation = translation;
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
