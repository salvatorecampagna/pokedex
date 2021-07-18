package com.truelayer.pokedex.translate.model;

import lombok.Builder;

import java.util.Objects;

@Builder
public class Translation {
    private final String translated;
    private final String text;
    private final String translation;

    public Translation(
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
        Translation that = (Translation) o;
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
                "Translation{translated='%s', text='%s', translation='%s'}",
                translated, text, translation
        );
    }
}
