package com.trustlayer.pokedex.translate.model;

import java.util.Objects;

public class TranslationRequest {
    private final String text;

    public TranslationRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslationRequest textDto = (TranslationRequest) o;
        return Objects.equals(text, textDto.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return String.format("TranslationRequest{text='%s'}", text);
    }
}
