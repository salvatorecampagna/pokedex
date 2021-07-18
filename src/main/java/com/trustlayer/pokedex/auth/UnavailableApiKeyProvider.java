package com.trustlayer.pokedex.auth;

import com.trustlayer.pokedex.auth.model.ApiKeyData;
import org.springframework.stereotype.Component;

//NOTE: this is just a 'stub' implementation. An actual implementation
//would retrieve the api key from some data source (another service, a database,...)
@Component
public class UnavailableApiKeyProvider implements ApiKeyProvider {
    @Override
    public ApiKeyData get() {
        return new ApiKeyData(false, "");
    }
}
