package com.truelayer.pokedex.auth.model;

import java.util.Objects;

public class ApiKeyData {
    private final Boolean valid;
    private final String apiKey;

    public ApiKeyData(
            final Boolean valid,
            final String apiKey
    ) {
        this.valid = valid;
        this.apiKey = apiKey;
    }

    public Boolean getValid() {
        return valid;
    }

    public String getApiKey() {
        return apiKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiKeyData that = (ApiKeyData) o;
        return Objects.equals(valid, that.valid) && Objects.equals(apiKey, that.apiKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valid, apiKey);
    }

    @Override
    public String toString() {
        return String.format(
                "ApiKeyData{isValid=%s, apiKey='%s'}",
                valid, apiKey
        );
    }
}
