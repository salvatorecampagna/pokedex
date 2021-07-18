package com.truelayer.pokedex.translate;

import com.truelayer.pokedex.details.model.Pokemon;
import org.springframework.stereotype.Service;

@Service
public interface TranslationClientProvider {
    TranslationClient get(Pokemon pokemon);
}
