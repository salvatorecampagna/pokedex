package com.trustlayer.pokedex.auth;

import com.trustlayer.pokedex.auth.model.ApiKeyData;

public interface ApiKeyProvider {
    ApiKeyData get();
}
