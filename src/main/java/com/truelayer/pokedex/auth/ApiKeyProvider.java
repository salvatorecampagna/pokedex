package com.truelayer.pokedex.auth;

import com.truelayer.pokedex.auth.model.ApiKeyData;

public interface ApiKeyProvider {
    ApiKeyData get();
}
