package com.truelayer.pokedex.translate.model;

import lombok.Builder;

import java.util.Objects;

@Builder
public class TranslationResponse {
    private Success success;
    private Contents contents;

    public TranslationResponse() {
    }

    public TranslationResponse(
            final Success success,
            final Contents contents
    ) {
        this.success = success;
        this.contents = contents;
    }

    public Success getSuccess() {
        return success;
    }

    public Contents getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslationResponse that = (TranslationResponse) o;
        return Objects.equals(success, that.success) && Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, contents);
    }

    @Override
    public String toString() {
        return String.format("TranslationResponse{success=%s, contents=%s}", success, contents);
    }
}
